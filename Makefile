# Chemins vers les dossiers source et de compilation
SRC_DIR = src/com/schottenTotten
CLASS_DIR = src/classes

# Trouver tous les fichiers .java dans les sous-dossiers
SOURCES_MODEL = $(shell find $(SRC_DIR)/model -name "*.java")
SOURCES_CONTROLLER = $(shell find $(SRC_DIR)/controller -name "*.java")
SOURCES_VIEW = $(shell find $(SRC_DIR)/view -name "*.java")
SOURCES_TESTS = $(shell find $(SRC_DIR)/tests -name "*.java")
SOURCES_AI = $(shell find $(SRC_DIR)/ai -name "*.java")

# Pour les diagrams
PLANTUML_CMD = plantuml
PUML_DIR = src/diagrams

# Cible par défaut : compile tout
all: controller

# Compilation des classes model
model: $(SOURCES_MODEL)
	@echo "Compilation des classes du modèle"
	mkdir -p $(CLASS_DIR)
	javac -d $(CLASS_DIR) $(SOURCES_MODEL)

# Compilation des classes ai qui dépendent de model
ai: model $(SOURCES_AI)
	@echo "Compilation des classes d'ia"
	javac -d $(CLASS_DIR) -cp $(CLASS_DIR) $(SOURCES_AI)


# Compilation des classes view
view: model $(SOURCES_VIEW)
	@echo "Compilation des classes de l'affichage"
	javac -d $(CLASS_DIR) -cp $(CLASS_DIR) $(SOURCES_VIEW)


# Compilation des classes controller, qui dépendent du modèle et de view
controller: view model ai $(SOURCES_CONTROLLER)
	@echo "Compilation des classes du contrôleur"
	javac -d $(CLASS_DIR) -cp $(CLASS_DIR) $(SOURCES_CONTROLLER)


# Compilation des tests, qui dépendent du modèle et du contrôleur
tests: controller $(SOURCES_TESTS)
	@echo "Compilation des tests"
	javac -d $(CLASS_DIR) -cp $(CLASS_DIR) $(SOURCES_TESTS)
	java -cp $(CLASS_DIR) com.schottenTotten.tests.Main_test


jeu: 
	@echo "Lancement du jeu.."
	java -cp $(CLASS_DIR) com.schottenTotten.controller.Jeu


diagram:
	@echo "Compilation des diagrams puml.."
	{ echo "@startuml all"; tail -n +2 $(PUML_DIR)/model.puml | head -n -1; tail -n +2 $(PUML_DIR)/ai.puml | head -n -1; tail -n +2 $(PUML_DIR)/jeu.puml | head -n -1; tail -n +2 $(PUML_DIR)/view.puml; } > $(PUML_DIR)/all.puml

	$(PLANTUML_CMD) $(shell find $(PUML_DIR) -name "*.puml")
		

# Nettoyage des fichiers compilés
clean:
	@echo "Nettoyage des fichiers compilés"
	rm -rf $(CLASS_DIR)

.PHONY: all model controller tests clean
