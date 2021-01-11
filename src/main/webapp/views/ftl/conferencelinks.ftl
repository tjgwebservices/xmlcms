<#import "/templatelinks.ftl" as templ/>

<#macro templateheading>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8" />
    <title><@templ.templatetitle /></title>
    <meta name="title" content="<@templ.templatetitle />" />
    <meta name="description" content="<@templ.templatedescription />" />
    <meta name="keywords" content="<@templ.templatedescription />" />
    <meta name="author" content="<@templ.templateauthor />">
    <meta property="og:title" content="<@templ.templateauthor />" />
    <meta property="og:description" content="<@templ.templateauthor />" />
    <meta property="og:image" content="https://tjgnews.com/img/tjgxmlcms2.png" />
    <meta property="og:url" content="//tjgwebservices.com.tjgxmlcms/" />
    <meta name="twitter:site" content="@NewsTjg" />
    <meta name="twitter:text:title" content="<@templ.templateauthor />" />
    <meta name="twitter:image" content="https://tjgnews.com/img/tjgxmlcms2.png?w=450" />
    <meta name="twitter:card" content="summary_large_image" />
    <meta name="robots" content="index, follow">
    <meta name="revisit-after" content="3 day">
    <meta name="viewport" content="width=device-width, initial-scale=1" />      
    <link rel="apple-touch-icon" sizes="180x180" href="/images/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/images/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/images/favicon-16x16.png">
    <link rel="stylesheet"
         type="text/css" href="<@spring.url '/css/style.css'/>"/>
    <link rel="stylesheet"
         type="text/css" href="<@spring.url '/css/chat.css'/>"/>
    <link rel="stylesheet"
         type="text/css" href="<@spring.url '/css/book.css'/>"/>
    <link rel="stylesheet"
         type="text/css" href="<@spring.url '/css/main.css'/>"/>
    <link rel="stylesheet"
         type="text/css" href="<@spring.url '/css/calendar.css'/>"/>
</#macro>
<#macro consultingheading>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8" />
    <title><@templ.templatetitle /></title>
    <meta name="title" content="<@templ.templatetitle />" />
    <meta name="description" content="<@templ.templatedescription />" />
    <meta name="keywords" content="<@templ.templatedescription />" />
    <meta name="author" content="<@templ.templateauthor />">
    <meta property="og:title" content="TJGXMLCMS" />
    <meta property="og:description" content="<@templ.templatedescription />" />
    <meta property="og:image" content="https://tjgnews.com/img/tjgxmlcms2.png" />
    <meta property="og:url" content="//tjgwebservices.com.tjgxmlcms/" />
    <meta name="twitter:site" content="@NewsTjg" />
    <meta name="twitter:text:title" content="<@templ.templatetexttitle />" />
    <meta name="twitter:image" content="https://tjgnews.com/img/tjgxmlcms2.png?w=450" />
    <meta name="twitter:card" content="summary_large_image" />
    <meta name="robots" content="index, follow">
    <meta name="revisit-after" content="3 day">
    <meta name="viewport" content="width=device-width, initial-scale=1" />      
    <link rel="apple-touch-icon" sizes="180x180" href="/images/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/images/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/images/favicon-16x16.png">
    <link rel="stylesheet"
       type="text/css" href="<@spring.url '/css/style.css'/>"/>
    <link rel="stylesheet"
       type="text/css" href="<@spring.url '/css/main.css'/>"/>
    <link rel="stylesheet"
       type="text/css" href="<@spring.url '/css/consulting.css'/>"/>
</#macro>


<#macro videos>

<@templ.templatevideos />

</#macro>

<#macro frames>

<@templ.templatewebsiteframes />

</#macro>


