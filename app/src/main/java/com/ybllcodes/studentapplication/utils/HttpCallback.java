package com.ybllcodes.studentapplication.utils;


import com.ybllcodes.studentapplication.pojo.ResultBean;

public interface HttpCallback {
    void setMain(ResultBean resultBean);
    void setMainForPControl(ResultBean resultBean);
    void setMainForSControl(ResultBean resultBean);
}
