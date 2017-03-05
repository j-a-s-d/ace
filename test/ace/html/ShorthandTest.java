/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.html;

import org.junit.Assert;
import org.junit.Test;

public class ShorthandTest {
    
    @Test public void testParse() {
        Assert.assertNull(Shorthand.parse(null));
        Assert.assertNull(Shorthand.parse(""));
        Assert.assertEquals("p", Shorthand.parse("p").tag);
        Assert.assertEquals("pre", Shorthand.parse("pre.big-text").tag);
        Assert.assertEquals("big-text", Shorthand.parse("pre.big-text").classes[0]);
        Assert.assertEquals("black", Shorthand.parse("pre.big-text.black").classes[1]);
        Assert.assertEquals("abc", Shorthand.parse("input#abc").id);
        Assert.assertEquals("abc", Shorthand.parse("input#abc.test").id);
        Assert.assertEquals("input", Shorthand.parse("input#abc.test").tag);
        Assert.assertEquals("test", Shorthand.parse("input#abc.test").classes[0]);
    }
    
    @Test public void testMake() {
        Assert.assertEquals("<p/>", Shorthand.make("p"));
        Assert.assertEquals("<p></p>", Shorthand.make("p", ""));
        Assert.assertEquals("<pre class='big-text'></pre>", Shorthand.make("pre.big-text", ""));
        Assert.assertEquals("<pre class='big-text black'></pre>", Shorthand.make("pre.big-text.black", ""));
        Assert.assertEquals("<input id='abc'/>", Shorthand.make("input#abc"));
        Assert.assertEquals("<input id='abc'>hello world!</input>", Shorthand.make("input#abc", "hello world!"));
        Assert.assertEquals("<input id='abc' class='test testing'>hello world!</input>", Shorthand.make("input#abc.test.testing", "hello world!"));
    }
    
}