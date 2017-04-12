<?php
//getting these lousy messages
$db = new PDO("mysql:host=localhost;dbname=swimmin2_Chat","swimmin2_root","none123");

//get messages
$query = $db->prepare("SELECT * FROM messages");
$query->execute();

//fetch
while($fetch = $query->fetch(PDO::FETCH_ASSOC))
{
	$name = $fetch['name'];
	$message = $fetch['message'];
	
	echo "<li class='cm'><b>".ucwords($name)."</b> - ".$message."</li>";
}
?>