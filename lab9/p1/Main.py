from abc import ABC, abstractmethod
import copy


class File(ABC):
    def __init__(self):
        self.title = ""
        self.author = ""
        self.paragraphs = []

    @abstractmethod
    def read_file_from_stdin(self):
        pass


class HTMLFile(File):
    def __init__(self):
        super().__init__()

    def read_file_from_stdin(self):
        self.title = input("Enter title: ")
        self.author = input("Enter author: ")
        num_paragraphs = int(input("Enter the number of paragraphs: "))
        for _ in range(num_paragraphs):
            paragraph = input("Enter paragraph: ")
            self.paragraphs.append(paragraph)

    def print_html(self):
        # Generare și afișare cod HTML
        html = f"<html>\n<head>\n<title>{self.title}</title>\n</head>\n<body>\n"
        html += f"<h1>{self.title}</h1>\n"
        html += f"<p>Author: {self.author}</p>\n"
        html += "<div>\n"
        for paragraph in self.paragraphs:
            html += f"<p>{paragraph}</p>\n"
        html += "</div>\n</body>\n</html>"
        print(html)


class JSONFile(File):
    def __init__(self):
        super().__init__()

    def read_file_from_stdin(self):
        self.title = input("Enter title: ")
        self.author = input("Enter author: ")
        num_paragraphs = int(input("Enter the number of paragraphs: "))
        for _ in range(num_paragraphs):
            paragraph = input("Enter paragraph: ")
            self.paragraphs.append(paragraph)

    def print_json(self):
        # Generare și afișare obiect JSON
        json_obj = {
            "title": self.title,
            "author": self.author,
            "paragraphs": self.paragraphs
        }
        print(json_obj)


class TextFile(File, ABC):
    def __init__(self):
        super().__init__()
        self.template = ""

    def print_text(self):
        # Afișare text formatat
        formatted_text = self.template.replace("<Template>", self.template)
        formatted_text = formatted_text.replace("<Titlu>", self.title)
        formatted_text = formatted_text.replace("<Autor>", self.author)
        content = "\n".join(self.paragraphs)
        formatted_text = formatted_text.replace("<paragraf1>", content)
        print(formatted_text)

    def clone(self):
        # Clonare obiect folosind modulul copy
        return copy.copy(self)


class ArticleTextFile(TextFile):
    def __init__(self):
        super().__init__()

    def print_text(self):
        # Afișare text formatat pentru articole
        formatted_text = f"    {self.title}\n" \
                         f"                    by {self.author}\n"
        content = "\n".join(self.paragraphs)
        formatted_text += content
        print(formatted_text)


class BlogTextFile(TextFile):
    def __init__(self):
        super().__init__()

    def print_text(self):
        # Afișare text formatat pentru blog-uri
        formatted_text = f"{self.title}\n"
        content = "\n".join(self.paragraphs)
        formatted_text += content + "\n\n"
        formatted_text += f"Written by {self.author}\n"
        print(formatted_text)


class FileFactory:
    @staticmethod
    def factory(file_type):
        if file_type == "HTML":
            return HTMLFile()
        elif file_type == "JSON":
            return JSONFile()
        elif file_type == "ArticleText":
            return ArticleTextFile()
        elif file_type == "BlogText":
            return BlogTextFile()
        else:
            raise ValueError("Invalid file type.")


def main():
    file_type = input("Enter file type (HTML, JSON, ArticleText, BlogText): ")
    file_factory = FileFactory()
    file = file_factory.factory(file_type)

    file.read_file_from_stdin()

    if isinstance(file, HTMLFile):
        file.print_html()
    elif isinstance(file, JSONFile):
        file.print_json()
    elif isinstance(file, TextFile):
        file.print_text()
    else:
        print("Invalid file type.")


if __name__ == '__main__':
    main()
