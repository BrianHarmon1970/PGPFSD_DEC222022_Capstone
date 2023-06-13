/* *****************************************
 * File: AuthStatus.java
 *  9/6/2022 B>S>H>
 */
package com.harmonengineering.authentication;

public enum AuthStatus
{
    NullUser,
    BadData_UserName ,
    BadData_Password ,
    BadData_ConfirmationPassword ,
    UserAdd_UserExists ,
    UserAdd_ConfirmationMismatch ,
    Authenticate_NoUser ,
    Authenticate_PasswordMismatch ,
    Validated ,
    Authenticated
}  ;