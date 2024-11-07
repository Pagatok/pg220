# Chemins vers les fichiers et dossiers
SRC_DIR = src
CLASS_DIR = $(SRC_DIR)/classes
PUML_FILE = $(SRC_DIR)/diagrams/model.puml
OUTPUT_FILE = model.png

# Commande pour PlantUML
PLANTUML_CMD = plantuml

# Cible par défaut : compile et génère le diagramme
all: compile diagram

# Compilation des fichiers Java
compile:
	mkdir -p $(CLASS_DIR)
	javac -d $(CLASS_DIR) -sourcepath $(SRC_DIR) $(SRC_DIR)/com/schottenTotten/Main_test.java

# Génération du diagramme avec PlantUML
diagram:
	$(PLANTUML_CMD) -o . $(PUML_FILE)

# Nettoyage des fichiers compilés et du diagramme
clean:
	rm -rf $(CLASS_DIR)/*
	rm -f $(OUTPUT_FILE)

.PHONY: all compile diagram clean
