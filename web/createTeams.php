<?php session_start(); 
error_reporting(E_ALL);
ini_set("display_errors", 1);
require_once("sql_connect.php");
var_dump($_POST);
if(!empty($_POST)) {
    $nbTeams = htmlspecialchars($_POST["nbTeams"]);
    $i=1;
    // On commence par reinitialier le jeu
    $res = get_res("DELETE FROM teams");
    while($i <= $nbTeams) {
        $team = htmlspecialchars($_POST["name".$i]);
        $leader = htmlspecialchars($_POST["leader".$i]);
        $color = htmlspecialchars($_POST["color".$i]);
        $res = get_res("INSERT INTO `teams` (`color`, `name`, `leader`, `coins`, `distance`, `go`, `limited`, `problem1`, `problem2`, `problem3`) VALUES
        ('$color', '$team', '$leader', 0, 0, 0, 0, 0, 0, 0);");
        $i++;
    }
    header("Location: dashboard.php");
    exit();
}
?>