package finalp;

import org.mindrot.jbcrypt.*;

public class TestJBcrypt {
    public static void testJBcrypt(){
        System.out.println("Testing JBcrypt...");

        String password = "myPassword123";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        
        System.out.println("Original: " + password);
        System.out.println("Hashed: " + hashed);
        
        // Verify password
        boolean matches = BCrypt.checkpw(password, hashed);
        System.out.println("Password matches: " + matches);
        
        // Try wrong password
        boolean wrongMatch = BCrypt.checkpw("wrongPassword", hashed);
        System.out.println("Wrong password matches: " + wrongMatch);
        
        System.out.println("✓ JBcrypt working!");
    }
}
