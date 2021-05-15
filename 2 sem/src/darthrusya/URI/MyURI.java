package darthrusya.URI;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyURI {

    //scheme://user:pass@host:port/path?query#fragment

    private String line;
    private String scheme, user, pass, host, port, path, query, fragment;

    public MyURI(String line) throws PseudoURISyntaxException {
        if (checkURI()) {
            this.line = line;
        }
        throw new PseudoURISyntaxException("failure");
    }

    public String getScheme() {
        this.scheme = get(2);
        return this.scheme;
    }

    public String getUser() {
        this.user = get(3);
        return this.user;
    }

    public String getPass() {
        this.pass = get(4);
        return this.pass;
    }

    public String getHost() {
        this.host = get(5);
        return this.host;
    }

    public String getPort() {
        this.port = get(7);
        return this.port;
    }

    public String getPath() {
        this.path = get(8);
        return this.path;
    }

    public String getQuery() {
        this.query = get(9);
        return this.query;
    }

    public String getFragment() {
        this.fragment = get(10);
        return this.fragment;
    }

    private String get(Integer index) {
        String regex = "^(?:(?:(([^:/#?]+:)?(?:(?://)(?:(?:(?:([^:@/#" +
                "?]+)(?::([^:@/#?]*))?)@)?(([^:/#?\\]\\[]+|\\[[^/\\]@#?]+])(?::([" +
                "0-9]+))?))?)?)?((?:/?(?:[^/?#]+/+)*)(?:[^?#]*)))?(\\?[^#]+)?)(#.*)?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(this.line);
        return m.group(index);
    }

    private boolean checkURI() {
        for (int i = 1; i < 11; i++) {
            if (get(i) == null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws PseudoURISyntaxException {

        MyURI ex = new MyURI("http://a:b@example.com:890/path/wah@t/foo.js?foo=" +
                "bar&bingobang=&king=kong@kong.com#foobar/bing/bo@ng?bang");
        System.out.println(ex.getQuery());

    }

}
