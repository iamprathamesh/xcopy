<?php


$response = array();


if (isset($_GET['title']) && isset($_GET['get'])) {
    
    
      
        $title = $_GET["title"];
        $get = $_GET["get"];
        $newline = "\n";
        $myfile = fopen("newfile.txt", "a") or die("Unable to open file!");
        //$txt = "title\n";
        fwrite($myfile, $title);
        fwrite($myfile, $newline);
        //$txt = "Jane Doe\n";
        fwrite($myfile, $get);
        fwrite($myfile, $newline);
        fclose($myfile);


    
} else {

    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";


    echo json_encode($response);
}
?>