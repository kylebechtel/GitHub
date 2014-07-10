<?
	if(invalidateSession()) die(failure("No Session Id"));
	
	include 'configuration.php';
	
    switch($_SERVER['REQUEST_METHOD']) {
    case 'GET': case 'POST': executeSecurityLayer(); break;
    default: die(failure($_SERVER['REQUEST_METHOD']." not allowed in Session"));
    }

	
	function invalidateSession()
	{
		return !isset($_POST['session']) && !isset($_GET['session']);
	}
	
	function failure($message)
	{
		return json_encode(array("success" => false, "reason" => $message));
	}
	
	function executeSecurityLayer()
	{
		$session = isset($_POST['session']) ? $_POST['session'] : $_GET['session'];
		$key = isset($_POST['key']) ? $_POST['key'] : $_GET['key'];

		switch($session) {
	    case $_ENV['drug']: $table = "drug"; break;
	    case $_ENV['doctor']: $table = "doctor"; break;
	    case $_ENV['patient']: $table = "patient"; break;
	    case $_ENV['address']: $table = "address"; break;
	    case $_ENV['pharmacist']: $table = "pharmacist"; break;
	    case $_ENV['insuranceco']: $table = "insuranceco"; break;
	    case $_ENV['prescription']: $table = "prescription"; break;
	    default: break;
	    }
	
		requestJsonFrom($table, $key);
	}
 
	function getSelectionDataFrom($table, $key)
	{
		return "";
		//return " WHERE doctorID = ".$key;
		//return " WHERE patientID = ".$key;
		//return " WHERE pharmacistID = ".$key;
	}
	
	function requestJsonFrom($table,$key)
	{
		if($table)
		{			
			$objs = array();
			$sqldata = mysql_query("SELECT * FROM $table".getSelectionDataFrom($table, $key));
			
			while($o = mysql_fetch_assoc($sqldata)) { $objs[] = $o; }
			echo json_encode(array("success" => true, $table => $objs));
			
		} else die(failure("Invalid Session Id"));
	}
?>