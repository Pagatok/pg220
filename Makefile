# Chemins vers les fichiers et dossiers
SRC_DIR = src
CLASS_DIR = $(SRC_DIR)/classes
PUML_FILE = $(SRC_DIR)/diagrams/model.puml
OUTPUT_FILE = model.png
SOURCES=$(shell find $(SRC_DIR) -name "*.java" ! -name "Borne.java")


# Commande pour PlantUML
PLANTUML_CMD = plantuml

# Cible par défaut : compile et génère le diagramme
all: compile



# Règle pour compiler tous les fichiers .java dans SRC_DIR
compile:
	javac -d $(CLASS_DIR) $(SOURCES)


run_tests:
	java -cp $(CLASS_DIR) com.schottenTotten.tests.Main_test

# Génération du diagramme avec PlantUML
diagram:
	$(PLANTUML_CMD) -o . $(PUML_FILE)

# Nettoyage des fichiers compilés et du diagramme
clean:
	rm -rf $(CLASS_DIR)/*
	rm -f $(OUTPUT_FILE)

.PHONY: all tests run_tests diagram clean
