package com.example.book.wages;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("wager")
public class WagesUserEntity {
    private String username;//员工姓名
    private Double wages;  //工资
    private Date date;  //发放月份
    private String level; //职级如 L5-3
    private Integer levelWages; //岗位工资
    private Integer deduction; //缺勤扣款
    private Double overtime; //加班费
    private Double basicSubsidy; //基本补贴
    private Double specialSubsidy; //专项补贴
    private Double otherReward; //其他奖励
    private Double totalWages; //应发工资总额
}
