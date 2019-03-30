package edu.smxy.associationmanagement.domain.stomp;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * @program: associationmanagement
 * @description: Stomp SendToUser 实现domain
 * @author: SDH
 * @create: 2019-03-30 22:33
 **/
public class StompUser implements Principal {
    //userid 或者 assid
    private String Id;
    
    public StompUser(String id) {
        Id = id;
    }
    
    @Override
    public String getName() {
        return Id;
    }
}
