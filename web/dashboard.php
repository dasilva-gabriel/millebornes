<?php session_start(); 
error_reporting(E_ALL);
ini_set("display_errors", 1);
require_once("sql_connect.php");
?>

<!DOCTYPE html>
<html lang="fr">
<head>
	<title>Dashboard</title>
	<meta charset="utf-8">
	
    <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>

<body>
    <h1>Le Mille Pistes</h1>
    <div class="box">
    <?php
        $res = get_res("SELECT * FROM teams;");

        while ($ligne = mysqli_fetch_assoc($res)) { ?>

            <div class="item">
                <div class="left">
                    <img src="img/teams/<?= $ligne['color'] ?>.png" width="100" height="100">
                    <?= $ligne["name"] ?>
                </div>
                <div class="center">
                    <div class="up">
                        <div class="up-left">
                            
                            <p>Chef: <?= $ligne["leader"] ?></p>
                            <img src="img/malus/problem1_<?= $ligne['problem1'] ?>.png" width="70" height="70">
                            <img src="img/malus/problem2_<?= $ligne['problem2'] ?>.png" width="70" height="70">
                            <img src="img/malus/problem3_<?= $ligne['problem3'] ?>.png" width="70" height="70">
                            <!--<img src="img/teams/<?= $ligne['color'] ?>.png" width="50" height="50">-->
                        </div>
                        <div class="up-right">
                            <img src="img/coins.png" width="50" height="50">
                            <div class="coins-value"><?= $ligne["coins"] ?></div>
                        </div>
                    </div>
                    <div class="down">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" style="width: <?php echo (int)$ligne["distance"]/10; ?>%; background-color:<?= $ligne["color"] ?>;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="1000"></div>
                        </div>
                        <div class="progress-number">
                           <p> Vous avez parcouru <?= $ligne["distance"]?> pistes </p>
                        </div>
                        
                    </div>
                </div>
                <div class="right">
                    <?php if($ligne['go'] == 0) {?>
                    <img src="img/feu_rouge.png" width="150" height="150">
                    <?php } else {?>
                    <img src="img/feu_vert.png" width="150" height="150">
                    <?php }?>
                </div>

            
            </div>
       <?php }

    ?>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

</html>