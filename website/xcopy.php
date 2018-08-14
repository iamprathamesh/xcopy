<?php


$response = array();


if (isset($_GET['title'])  && isset($_GET['copy'])) {
    
    
      
        $title = $_GET["title"];
        $copy = $_GET["copy"];
        //$b_name = $_GET["b_name"];
        //$b_author = $_GET["b_author"];
        //$b_edition = $_GET["b_edition"];
        //$b_isbn = $_GET["b_isbn"];
        //$b_category = $_GET["b_category"];
        //$b_mrp = $_GET["b_mrp"];
        //$b_sp = $_GET["b_sp"];
        //$b_condition = $_GET["b_condition"];
        //$b_desc = $_GET["b_desc"];
        //$b_date_created = $_GET["b_date_created"];
        //$u_city = $_GET["u_city"];
        //$image_url = $_GET["image_url"];


    require_once __DIR__ . '/db_connect.php';


    $db = new DB_CONNECT();


    $result = mysql_query("INSERT INTO xcopy(title,copy) VALUES('$title',  '$copy')");


    if ($result) {

        $response["success"] = 1;
        $response["message"] = "copied successfully!";


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