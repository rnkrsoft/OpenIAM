package com.rnkrsoft.opensource.iam.domains;

import com.rnkrsoft.opensource.iam.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FetchUsersRequest implements Serializable{
    @ApidocElement(value = "用户状态", enumClass = UserStatus.class, defaults = "1")
    Integer userStatus;
}
