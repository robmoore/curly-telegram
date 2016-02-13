package hello;

import com.stormpath.sdk.servlet.account.AccountResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(HttpServletRequest req) {
        // can't get here without authenticating
        String name = AccountResolver.INSTANCE.getAccount(req).getFullName();

        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
