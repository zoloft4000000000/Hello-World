<?php
	if(!isset($_REQUEST["name"])) {
	die('INVALID REQUEST');}
	
	$name = $_REQUEST["name "];
	echo 'oh hi ' . $name . ':) how are you?';  



?>


