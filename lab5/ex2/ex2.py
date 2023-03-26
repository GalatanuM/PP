import sys
import random
import time
from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QVBoxLayout, QTextEdit, QPushButton, QHBoxLayout, QFileDialog, QInputDialog

class AplicatieJurnal(QWidget):
    def __init__(self):
        super().__init__()
        self.initializare()

    def initializare(self):
        self.setWindowTitle('Aplicatie tip jurnal')

        citate = open('citate.txt', 'r').readlines()
        self.citat = random.choice(citate)
        self.label_Citat = QLabel(self.citat)
        self.label_Citat.setWordWrap(True)

        hbox = QHBoxLayout()  # create a horizontal layout
        self.Edit = QTextEdit()
        hbox.addWidget(self.Edit)

        vbox2 = QVBoxLayout()

        load_btn = QPushButton('Incarcare fisier')
        load_btn.clicked.connect(self.incarcare_Text)
        vbox2.addWidget(load_btn)

        save_btn = QPushButton('Salvare fisier')
        save_btn.clicked.connect(self.salvare_Text)
        vbox2.addWidget(save_btn)

        reload_btn = QPushButton('Reload citat')
        reload_btn.clicked.connect(self.reincarca_citat)
        vbox2.addWidget(reload_btn)

        add_btn = QPushButton('Adauga citat')
        add_btn.clicked.connect(self.adauga_citat)
        vbox2.addWidget(add_btn)

        hbox.addLayout(vbox2)  # add the vbox2 layout to the hbox

        vbox = QVBoxLayout()
        vbox.addWidget(self.label_Citat)
        vbox.addLayout(hbox)  # add the hbox layout to the vbox

        self.setLayout(vbox)

    def incarcare_Text(self):
        fisier, _ = QFileDialog.getOpenFileName(self, 'Incarcare fisier')
        if fisier:
            self.Edit.setText(open(fisier, 'r').read())

    def salvare_Text(self):
        fisier, _ = QFileDialog.getSaveFileName(self, 'Salvare fisier', f'jurnal_{time.time()}.txt')
        if fisier:
            open(fisier, 'w').write(self.Edit.toPlainText())

    def reincarca_citat(self):
        citate = open('citate.txt', 'r').readlines()
        self.citat = random.choice(citate)
        self.label_Citat.setText(self.citat)

    def adauga_citat(self):
        citat_nou, ok = QInputDialog.getText(self, 'Adauga citat nou', 'Introduceti un citat:')
        if ok:
            open('citate.txt', 'a').write(citat_nou + '\n')


if __name__ == '__main__':
    app = QApplication(sys.argv)
    jurnal = AplicatieJurnal()
    jurnal.show()
    sys.exit(app.exec_())
