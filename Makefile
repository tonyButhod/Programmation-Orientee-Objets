# Ensimag 2A POO - TP 2016/17
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

OBJ:=$(wildcard src/objects/*.java)
IO:=$(wildcard src/io/*.java)
ANIM:=$(wildcard src/animation/*.java)
STRAT:=$(wildcard src/strategie/*.java)
EXCEPT:=$(wildcard src/exception/*.java)

all: testInvader testLecture 

TestInvader: src/TestInvader.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestInvader.java

TestLecture: src/TestLecteurDonnees.java
	javac -d bin -sourcepath src src/TestLecteurDonnees.java


TestCase: src/TestCase.java
	javac -d bin -sourcepath src src/TestCase.java

TestCarte: io objects animation src/TestCarte.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestCarte.java

TestSimu: objects animation src/TestSimulateur.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestSimulateur.java

TestDeplacement: io objects animation exception src/TestDeplacement.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestDeplacement.java

TestRemplissage: io objects animation exception src/TestRemplissage.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestRemplissage.java

TestIntervention: io objects animation exception src/TestIntervention.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestIntervention.java

TestStrat: objects animation src/TestStrategie.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestStrategie.java

TestDijkstra: objects animation strategie src/TestDijkstra.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestDijkstra.java

TestIncendie: objects src/TestIncendie.java
	javac -d bin -sourcepath src src/TestIncendie.java

TestDonnees: objects src/TestDonnees.java
	javac -d bin -classpath bin/gui.jar -sourcepath src  src/TestDonnees.java

TestRobot: objects src/TestRobot.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestRobot.java

TestChefRobot: objects src/TestChefRobot.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestChefRobot.java

io: $(IO)
	javac -d bin -classpath bin/gui.jar -sourcepath src $^

objects: $(OBJ)
	javac -d bin -classpath bin/gui.jar -sourcepath src $^

exception: $(EXCEPT)
	javac -d bin -classpath bin/gui.jar -sourcepath src $^

animation: $(ANIM)
	javac -d bin -classpath bin/gui.jar -sourcepath src $^

strategie: $(STRAT)
	javac -d bin -classpath bin/gui.jar -sourcepath src $^

javadoc:
	javadoc -encoding utf8 -docencoding utf8 -charset utf8 -private -d doc_cplt -sourcepath src $(STRAT) $(ANIM) $(EXCEPT) $(OBJ) $(IO) -classpath bin/gui.jar

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exeInvader: TestInvader TestLecture
	java -classpath bin:bin/gui.jar TestInvader

exeLecture: TestLecture
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map

exeTestCase: TestCase
	java -classpath bin TestCase

exeTestCarte: TestCarte
	java -classpath bin:bin/gui.jar TestCarte

exeTestSimu: TestSimu
	java -classpath bin:bin/gui.jar TestSimulateur

exeTestDeplacement: TestDeplacement
	java -classpath bin:bin/gui.jar TestDeplacement

exeTestRemplissage: TestRemplissage
	java -classpath bin:bin/gui.jar TestRemplissage

exeTestIntervention: TestIntervention
	java -classpath bin:bin/gui.jar TestIntervention

exeTestStrat: TestStrat
	java -classpath bin:bin/gui.jar TestStrategie

exeTestDijkstra: TestDijkstra
	java -classpath bin:bin/gui.jar TestDijkstra

exeTestIncendie: TestIncendie
	java -classpath bin:bin/gui.jar TestIncendie

exeTestDonnees: TestDonnees
	java -classpath bin:bin/gui.jar TestDonnees

exeTestRobot: TestRobot
	java -classpath bin:bin/gui.jar TestRobot

exeTestChefRobot: TestChefRobot
	java -classpath bin:bin/gui.jar TestChefRobot $(MAP)
clean:
	rm -rf bin/src/ bin/exception/ bin/*.class bin/io/*.class bin/objects/*.class bin/animation/*.class bin/strategie/*.class src/*~ src/objects/*~ src/animation/*~ src/strategie/*~
