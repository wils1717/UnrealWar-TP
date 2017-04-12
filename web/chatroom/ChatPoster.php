<?php
//the legendary chat poster
$db = new PDO("mysql:host=localhost;dbname=swimmin2_Chat","swimmin2_root","none123");

//secure the chat
if(isset($_POST['text']) && isset($_POST['name']))
{
	$text = strip_tags(stripslashes($_POST['text']));
	$name = strip_tags(stripslashes($_POST['name']));
	
	if(!empty($text) && !empty($name))
	{
		$insert = $db->prepare("INSERT INTO messages VALUES('','".$name."','".$text."')");
		$insert->execute();
		
		echo "<li class='dm'><b>".ucwords($name)."</b> - ".$text."</li>";
	}
}

?>