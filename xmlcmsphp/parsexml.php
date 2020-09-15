<?php
$doc = new DOMDocument();
$doc->load( {xml_source} );
$heading = $doc->getElementsByTagName( "heading" )->item(0)->nodeValue;
$a = array();
$l = array();
$g = array();
$articles = $doc->getElementsByTagName( "article" );
$links = $doc->getElementsByTagName( "link" );
$groups = $doc->getElementsByTagName( "group" );
foreach( $articles as $article )
{
	$ea = array();
	$titles = $article->getElementsByTagName( "title" );
	$title = $titles->item(0)->nodeValue;
	$sections = $article->getElementsByTagName( "section" );
	$section = $sections->item(0)->nodeValue;
	array_push($ea, $title, $section);
	array_push($a, $ea);
}
foreach( $links as $link )
{
	$ahref = $link->nodeValue;
	array_push($l, $ahref);
}
foreach( $groups as $group )
{
	$els = array();	
	array_push($els, "groupId", $group->getAttribute( "name" ));
	$elements = $group->getElementsByTagName( "element" );
	foreach ($elements as $element) {
		$elemname = $element->getAttribute( "name" );
		$elemvalue = $element->getAttribute( "value" );
		array_push($els, $elemname, $elemvalue);
	}
	array_push($g, $els);
}
?> 
