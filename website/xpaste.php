<?php



$response = array();

require_once __DIR__ . '/db_connect.php';


$db = new DB_CONNECT();

if (isset($_GET["title"])) {

    $title = $_GET['title'];
    
    



$result = mysql_query("SELECT copy FROM xcopy where title = $title") or die(mysql_error());


if (mysql_num_rows($result) > 0) {


    $response["copied"] = array();
    
    while ($row = mysql_fetch_array($result)) {

        $copied = array();
        $copied["title"] = $row["title"];
        $copied["copy"] = $row["copy"];
        //$books["b_name"] = $row["b_name"];
        //$books["b_author"] = $row["b_author"];
        //$books["b_edition"] = $row["b_edition"];
        //$books["b_isbn"] = $row["b_isbn"];
        //$books["b_category"] = $row["b_category"];
        //$books["b_mrp"] = $row["b_mrp"];
        //$books["b_sp"] = $row["b_sp"];
        //$books["b_condition"] = $row["b_condition"];
        //$books["b_desc"] = $row["b_desc"];
        //$books["b_date_created"] = $row["b_date_created"];
        //$books["u_city"] = $row["u_city"];
        //$books["image_url"] = $row["image_url"];



        $response["message"] = "Copied Successfully!";
        array_push($response["copied"], $copied);
    }

    $response["success"] = 1;


    echo json_encode($response);
} else {

    $response["success"] = 0;
    $response["message"] = "Oops! An error occurred.";


    echo json_encode($response);
}
} else {

    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";


    echo json_encode($response);
}
?>
