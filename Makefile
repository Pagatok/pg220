# Chemins vers les fichiers et dossiers
SRC_DIR = src
CLASS_DIR = $(SRC_DIR)/classes
DIAGRAMS_DIR = src/diagram
SOURCES_MODEL =$(shell find $(SRC_DIR)/com/schottenTotten/model -name "*.java")
SOURCES_CONTROLLER = $(shell find $(SRC_DIR)/com/schottenTotten/controller -name "*.java")
SOURCES_DIAGRAMS = $(shell find $(SRC_DIR)/diagrams -name "*.puml")
SOURCES_TESTS = $(shell find $(SRC_DIR)/com/schottenTotten/tests -name "*.java")

# Commande pour PlantUML
PLANTUML_CMD = plantuml

# Cible par défaut : compile et génère le diagramme
all: model controller



# Règle pour compiler tous les fichiers .java dans SRC_DIR
model:
	javac -d $(CLASS_DIR) $(SOURCES_MODEL)

controller:
	javac -d $(CLASS_DIR) $(SOURCES_CONTROLLER)

run_tests: model
	javac -d $(CLASS_DIR) $(SOURCES_TESTS)
	java -cp $(CLASS_DIR) com.schottenTotten.tests.Main_test

# Génération du diagramme avec PlantUML
diagram:
	$(PLANTUML_CMD) -o . $(SOURCES_DIAGRAMS)

# Nettoyage des fichiers compilés et des diagramme
clean:
	rm -rf $(CLASS_DIR)/*
	rm -f src/diagrams/*.png

.PHONY: all model controller run_tests diagram clean
