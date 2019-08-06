package com.carter.javaAndroid.modules.todo.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.core.event.RefreshTodoEvent;
import com.carter.javaAndroid.modules.todo.bean.TodoItemBean;
import com.carter.javaAndroid.modules.todo.contract.AddTodoContract;
import com.carter.javaAndroid.modules.todo.presenter.AddTodoPresenter;
import com.carter.javaAndroid.utils.CommonUtils;
import com.carter.javaAndroid.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static com.carter.javaAndroid.core.constant.Constants.TODO_TYPE;
import static com.carter.javaAndroid.core.constant.Constants.TODO_TYPE_ALL;
import static com.carter.javaAndroid.core.constant.Constants.TODO_TYPE_LIFE;
import static com.carter.javaAndroid.core.constant.Constants.TODO_TYPE_OTHER;
import static com.carter.javaAndroid.core.constant.Constants.TODO_TYPE_STUDY;
import static com.carter.javaAndroid.core.constant.Constants.TODO_TYPE_WORK;

@Route(path = ARouterPath.ADD_TODO_ACTIVITY)
public class AddTodoActivity extends BaseActivity<AddTodoPresenter> implements AddTodoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.et_add_todo_title)
    EditText mAddTodoTitle;
    @BindView(R.id.et_add_todo_content)
    EditText mAddTodoContent;
    @BindView(R.id.rg_todo_priority)
    RadioGroup mPriorityRg;
    @BindView(R.id.rb_todo_priority_1)
    RadioButton mTodoPriority1;
    @BindView(R.id.rb_todo_priority_2)
    RadioButton mTodoPriority2;
    @BindView(R.id.tv_add_todo_label_content)
    TextView mAddTodoLabel;
    @BindView(R.id.tv_add_todo_date_content)
    TextView mAddTodoDate;

    private SparseArray<String> mTodoLabelArray = new SparseArray<>(5);
    String choiceLabel;
    private int mTodoId = -1;
    private int mTodoStatus = 0;
    private AlertDialog mDialog;

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_todo;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        mTodoLabelArray.put(TODO_TYPE_ALL, getString(R.string.todo_no_label));
        mTodoLabelArray.put(TODO_TYPE_WORK, getString(R.string.todo_work));
        mTodoLabelArray.put(TODO_TYPE_STUDY, getString(R.string.todo_study));
        mTodoLabelArray.put(TODO_TYPE_LIFE, getString(R.string.todo_life));
        mTodoLabelArray.put(TODO_TYPE_OTHER, getString(R.string.todo_other));

        TodoItemBean TodoItemBean = (TodoItemBean) getIntent().getSerializableExtra(Constants.TODO_DATA);
        if (TodoItemBean != null) {
            mTodoId = TodoItemBean.getId();
            mTodoStatus = TodoItemBean.getStatus();
            mTitle.setText(R.string.todo_edit_title);
            mAddTodoTitle.setText(TodoItemBean.getTitle());
            mAddTodoContent.setText(TodoItemBean.getContent());
            if (TodoItemBean.getPriority() == 1) {
                mTodoPriority1.setChecked(true);
                mTodoPriority2.setChecked(false);
            } else {
                mTodoPriority1.setChecked(false);
                mTodoPriority2.setChecked(true);
            }

            mAddTodoLabel.setText(mTodoLabelArray.get(TodoItemBean.getType()));

            if (TextUtils.isEmpty(TodoItemBean.getCompleteDateStr())) {
                mAddTodoDate.setText(TodoItemBean.getDateStr());
            } else {
                mAddTodoDate.setText(TodoItemBean.getCompleteDateStr());
            }

        } else {
            mTodoId = -1;
            mTitle.setText(R.string.todo_new_title);
            mAddTodoLabel.setText(mTodoLabelArray.get(getIntent().getIntExtra(TODO_TYPE, 0)));
            mAddTodoDate.setText(CommonUtils.getCurrentDate());
        }
    }

    @OnClick({R.id.tv_add_todo_label_content, R.id.tv_add_todo_date_content,
            R.id.iv_label_arrow_right, R.id.iv_date_arrow_right,
            R.id.bt_todo_save})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_todo_label_content:
            case R.id.iv_label_arrow_right:
                choiceLabel = mAddTodoLabel.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.bt_choose_label);
                builder.setSingleChoiceItems(R.array.todo_labels,
                        mTodoLabelArray.indexOfValue(choiceLabel),
                        (dialog, which) -> choiceLabel = mTodoLabelArray.get(which));
                builder.setPositiveButton(R.string.ok,
                        (dialog, which) -> mAddTodoLabel.setText(choiceLabel));
                builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
                });
                AlertDialog alertDialog = builder.show();
                break;
            case R.id.tv_add_todo_date_content:
            case R.id.iv_date_arrow_right:
                Calendar calendar = CommonUtils.dateString2Calendar(mAddTodoDate.getText().toString());
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view1, year, month, dayOfMonth) -> mAddTodoDate.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth)), calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.bt_todo_save:
                HashMap<String, Object> map = new HashMap<>();
                map.put(Constants.KEY_TODO_TITLE, mAddTodoTitle.getText().toString());
                map.put(Constants.KEY_TODO_CONTENT, mAddTodoContent.getText().toString());
                map.put(Constants.KEY_TODO_DATE, mAddTodoDate.getText().toString());
                map.put(Constants.KEY_TODO_TYPE, mTodoLabelArray.indexOfValue(
                        mAddTodoLabel.getText().toString()));
                map.put(Constants.KEY_TODO_STATUS, mTodoStatus);
                map.put(Constants.KEY_TODO_PRIORITY, mTodoPriority1.isChecked() ?
                        Constants.TODO_PRIORITY_FIRST : Constants.TODO_PRIORITY_SECOND);

                if (mTodoId == -1) {
                    mPresenter.addTodo(map);
                } else {
                    mPresenter.updateTodo(mTodoId, map);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void addTodoSuccess(TodoItemBean TodoItemBean) {
        EventBus.getDefault().post(new RefreshTodoEvent(-1));
        ToastUtils.showToast(this, getString(R.string.add_todo_success));
        finish();
    }

    @Override
    public void updateTodoSuccess(TodoItemBean TodoItemBean) {
        ToastUtils.showToast(this, getString(R.string.update_todo_success));
        EventBus.getDefault().post(new RefreshTodoEvent(-1));
        finish();
    }

    @Override
    public void showLoading() {
        if (mDialog == null) {
            mDialog = CommonUtils.getLoadingDialog(this, getString(R.string.saving));
        }
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
