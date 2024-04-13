import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

// import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

// https://jwt.io
public class JWTTest {
    @Test
    public void testGenerateJWT(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("name", "jacky");
        claims.put("age", 24);

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "jackylee")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .compact();
        System.out.println(jwt);
    }

    // @Test
    public void testParseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("jackylee")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiamFja3kiLCJleHAiOjE3MDMzMzQxNzcsImFnZSI6MjR9.8hZ6WNwq8MFRCCXm8NrSf_gssFeLVTR3YW6iCLXjvMY")
                .getBody();
        System.out.println(claims);


    }
}
