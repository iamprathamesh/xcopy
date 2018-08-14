<?php


$response = array();


if (isset($_GET['title']) && isset($_GET['get'])) {
    
    
      
        $title = $_GET["title"];
        $get = $_GET["get"];
        //$share = $_GET["share"];
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
        $myfile = fopen("newfile.txt", "w") or die("Unable to open file!");
        //$txt = "title\n";
        fwrite($myfile, $title);
        //$txt = "Jane Doe\n";
        fwrite($myfile, $get);
        fclose($myfile);


    //require_once __DIR__ . '/db_connect.php';


    //$db = new DB_CONNECT();


    //$result = mysql_query("SELECT copy FROM xcopy WHERE title='$title'");
    //$delete = mysql_query("DELETE FROM xcopy WHERE date - CURRENT_TIMESTAMP > 1");
    //$row = mysql_fetch_row($result);

    //if ($result && $row) {

        //$response["success"] = 1;
        //$response["message"] = "copied successfully!";

        
        //echo '<script src="dist/clipboard.min.js"></script>';
        //echo '<script language="javascript" value="https://github.com/zenorocha/clipboard.js.git" data-clipboard-target="';   echo $row[0];  echo '">';
        //echo  "new Clipboard('";      echo $row[0];      echo"')";
        //echo 'alert("Got It!")';
        //echo '</script>';
        //echo "<script>window.location = 'http://xcopy.atwebpages.com/'</script>";
        //echo '<p value="https://github.com/zenorocha/clipboard.js.git" data-clipboard-target="'   $row[0]    echo '"></p';
        //echo htmlspecialchars($row[0]);
    //} else {

        //$response["success"] = 0;
        //$response["message"] = "Oops! An error occurred.";
        
        //echo '<script language="javascript">';
        //echo 'alert("Check your Title ID!")';
        //echo '</script>';                             
        //echo "<script>window.location = 'http://xcopy.atwebpages.com/'</script>";
    //}
} else {

    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";


    echo json_encode($response);
}
?>