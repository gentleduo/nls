package org.duo.nls.business.enums;

public enum SystemErrorMsg {


    NOT_FOUND_ERROR("服务器找不到您所请求的文件或脚本，请检查URL以确保路径正确"),
    INTERNAL_SERVER_ERROR("服务器内部错误，请联系管理员"),
    UNEXPECTED_ERROR("系统出现未知异常，请联系管理员"),
    ;
    private String desc;

    SystemErrorMsg(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
