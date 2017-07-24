package core.testLibrary;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FindShadowDom extends By {
    String id;
    String classname;
    String tagname;

    @Override
    public List<WebElement> findElements(SearchContext arg0) {
        System.out.println("#########################");
        System.out.println(arg0);
        System.out.println("#########################");
        WebDriver driver = ((WebDriver) arg0);
        
        System.out.println(driver.getClass());
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> element = (List<WebElement>) js.executeScript("var metadata={id:arguments[0],className:arguments[1],tagName:arguments[2]};window.test=function(){var b=function(d){this.rootdom=d,this.shadowRoots,this.shadowRootParents,this.allShadowRoots(d)};b.prototype=function(){var d=function(e){let f=document.createNodeIterator(e,NodeFilter.SHOW_ALL,function(j){if(j.shadowRoot)return!0});for(var h,g=[];h=f.nextNode();)g.push(h);return g};return{allShadowRoots:function(){for(var h,f=d(document.body),g=0;g<f.length;g+=1)h=f[g].shadowRoot,Array.prototype.push.apply(f,d(h));this.shadowRootParents=f;var j=f.map(function(k){return k.shadowRoot});return this.shadowRoots=j,j},getShadowRootUsingMetadata:function(e){var f=Object.getOwnPropertyNames(e);f=f.filter(function(j){if(e[j])return!0});var g=this.shadowRootParents.filter(function(j){var k=f.every(function(l){return j[l]===e[l]});return k}),h=g.map(function(j){return j.shadowRoot});return h}}}();var c=new b(document.body);return c.getShadowRootUsingMetadata(metadata)},console.log(window.test(metadata));return window.test(metadata);"
                ,this.id,this.classname,this.tagname);
        
        return element;
    }
    
    public FindShadowDom(Map<String, String> args) {
        this.id = args.get("id");
        this.classname = args.get("name");
        this.tagname = args.get("tagname");
        
        System.out.println(this.id);
        System.out.println(this.classname);
        System.out.println(this.tagname);
    }
    
    
}