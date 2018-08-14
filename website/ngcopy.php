<?php


$response = array();


if (isset($_GET['title'])  && isset($_GET['copy']) && isset($_GET['share'])) {
    
    
      
        $title = $_GET["title"];
        $copy = $_GET["copy"];
        $share = $_GET["share"];
        //$newcopy = htmlspecialchars($copy);
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

    if ($copy) {
    $result = htmlspecialchars(mysql_query("INSERT INTO xcopy(title,copy) VALUES('$title',  '$copy')"));
    $delete = mysql_query("DELETE FROM xcopy WHERE date - CURRENT_TIMESTAMP > 1");
    //$row = mysql_fetch_row($result);
    } 

    if ($result) {

        $response["success"] = 1;
        $response["message"] = "copied successfully!";

        echo '<script language="javascript">';
        echo 'alert("Copied Sucessfully!")';
        echo '</script>'; 
        //$url = 'http://xcopy.atwebpages.com/';
        echo "<script>window.location = 'http://xcopy.atwebpages.com/'</script>";
        //header( "Location: $url" );
        //include("index.php");
    } else {

        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        
        echo '<script language="javascript">';
        echo 'alert("Title ID Already Used! or Required Fields are missing!")';
        echo '</script>';                             
        echo "<script>window.location = 'http://xcopy.atwebpages.com/'</script>";

                                       
    }
} else {

    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";


    echo json_encode($response);
}
?>