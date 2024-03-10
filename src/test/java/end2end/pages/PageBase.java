package end2end.pages;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageBase {

    protected static final Logger logger = LoggerFactory.getLogger(PageBase.class);

    public PageBase() {
        checkPage();
    }

    protected void checkPage() {}

}
