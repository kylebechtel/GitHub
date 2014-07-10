<? 
	$host="localhost"; // Host name 
	$username=""; // Mysql username 
	$password=""; // Mysql password 
	$db_name="mywargam_pharmacist_prescription_drug_system"; // Database name 
	
	// Connect to server and select databse.
	mysql_connect("$host", "$username", "$password") or die("cannot connect"); 
	mysql_select_db("$db_name") or die("cannot select DB");
	
	$_ENV['drug'] = md5("drug~kcbkahryrrylliaesn");
	$_ENV['doctor'] = md5("doctor~kcbkahryrrylliaesn");
	$_ENV['patient'] = md5("patient~kcbkahryrrylliaesn");
	$_ENV['address'] = md5("address~kcbkahryrrylliaesn");
	$_ENV['pharmacist'] = md5("pharmacist~kcbkahryrrylliaesn");
	$_ENV['insuranceco'] = md5("insuranceco~kcbkahryrrylliaesn");
	$_ENV['prescription'] = md5("prescription~kcbkahryrrylliaesn");
?>