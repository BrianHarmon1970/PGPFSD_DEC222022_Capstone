/* *****************************************
 * File: AuthenticateResult.java
 *  9/6/2022 B>S>H>
 */
package com.harmonengineering.authentication ;
import com.harmonengineering.beans.User;

public class AuthenticateResult
{

    private final AuthStatus m_ResultState ;
    private final User m_User ;
    AuthenticateResult(AuthStatus resultState, User user )
    {
        m_ResultState = resultState;
        m_User = user ;
    }
    public User getUser() { return m_User ; };
    public AuthStatus getResultStatus() { return m_ResultState ; }
}
