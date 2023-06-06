package com.ybllcodes.studentapplication.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HraControl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer status;
    private String setInstallPackageWhiteList;
    private String setWhiteUrlList;
    private Integer setNetFirwall;
    private String stuMac;
    private String parMac;

    public HraControl(String pMac,String sMac){
        this.parMac = pMac;
        this.stuMac = sMac;
        init();
    }
    public void init(){
        status = 1;
        setNetFirwall = 0;
    }
}