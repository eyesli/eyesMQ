package com.levil.eyesmq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model implements Serializable {
        private Integer count;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        private Date invoiceDate;
}