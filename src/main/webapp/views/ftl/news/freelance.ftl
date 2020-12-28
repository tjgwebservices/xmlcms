<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <main>
        <h2>Freelance News</h2>

        <h4>News Feed</h4>
        <p><#if rssFeed??>${rssFeed.channelTitle}</#if></p>
        <p><#if rssFeed??>${rssFeed.channelLink}</#if></p>
        <p><#if rssFeed??>${rssFeed.channelDescription}</#if></p>

        <article>
        <#if rssFeed??>
        <#list rssFeed.items as item>
        <section>

        <h2><a href="${item[1]}">${item[0]}</a></h2>
        <p>${item[2]}</p>
        </section>

        </#list>
        </#if>
        </article>

        <article>
            <section>
                <h4>News</h4>
                <p>International News</p>
            </section>
            <section>
                <h4>News</h4>
                <p>World Events</p>
            </section>
            <section>
                <h4>News</h4>
                <p>Global News</p>
            </section>
        </article>



     </main>
   <@nav.footing />


