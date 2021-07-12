package org.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NanjingUni {
    @ExcelIgnore
    Integer id;
    String department;
    String major;
    String studyMethod;
    Integer politics;
    Integer foreignLanguage;
    Integer pro_course1;
    Integer pro_course2;
    Integer totalScores;
    String remark;
}
