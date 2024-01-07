JAVA=/usr/bin/java
JAVAC=/usr/bin/javac
JAVADOC=/usr/bin/javadoc
PY=scripts/venv/bin/python
PYTHON=/usr/bin/python3
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
SCRIPTS=scripts
DOCS=docs

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

BIN_CLASSES=BinaryTreeNode.class BinaryTree.class BinarySearchTree.class FileHandler.class Vaccine.class VaccineArray.class VaccineArrayApp.class VaccineBSTApp.class

CLASSES=$(BIN_CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASSES)

clean:
	$(RM) $(BINDIR)/*.class

run-array: $(CLASSES)
	$(JAVA) -cp $(BINDIR) VaccineArrayApp $(IN)

run-bst: $(CLASSES)
	$(JAVA) -cp $(BINDIR) VaccineBSTApp $(IN)

run-part1:
	$(PY) $(SCRIPTS)/run_part_1.py

run-part2:
	$(PY) $(SCRIPTS)/run_part_2.py

clean-data:
	rm -r output/* && mkdir output/part_1 output/part_2 output/part_2/operations

doccer:
	$(JAVADOC) -sourcepath ./$(SRCDIR) **/*.java -d ./$(DOCS)

clean-docs:
	rm -r $(DOCS)/*

install-venv:
	$(PYTHON) -m venv scripts/venv && source scripts/venv/bin/activate && pip install -r scripts/requirements.txt
