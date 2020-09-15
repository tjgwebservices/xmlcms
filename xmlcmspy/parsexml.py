class XMLDoc(object):
	def __init__(self, doc):
		self.doc = doc

	def retrieveValue(self,value):
		return self.doc.getElementsByTagName(value)[0].childNodes[0].nodeValue.strip()

	def retrieveLinks(self,value):
		l = []
		links = self.doc.getElementsByTagName(value)[0].getElementsByTagName('link')
		for link in links:
			l.append(link.childNodes[0].nodeValue.strip())
		return l

	def retrieveFigures(self):
		f = []
		figures=self.doc.getElementsByTagName('figures')[0].getElementsByTagName('figure')
		for figure in figures:
			ef = []
			title = figure.getElementsByTagName('title')[0].childNodes[0].nodeValue.strip()
			description = figure.getElementsByTagName('description')[0].childNodes[0].nodeValue.strip()
			list = figure.getElementsByTagName('list')[0].getElementsByTagName('element')
			listarray = []
			for element in list:
				listarray.append(element.childNodes[0].nodeValue.strip())
			ef.append([title,description,listarray])
			f.append(ef)
		return f

	def retrieveArticles(self):
		a = []
		articles=self.doc.getElementsByTagName('articles')[0].getElementsByTagName('article')
		for article in articles:
			ea = []
			title = article.getElementsByTagName('title')[0].childNodes[0].nodeValue.strip()
			section = article.getElementsByTagName('section')[0].childNodes[0].nodeValue.strip()
			if article.getElementsByTagName('image'):
				image = article.getElementsByTagName('image')[0].childNodes[0].nodeValue.strip()
				ea.append([title,section,image])
			else:	
				ea.append([title,section])
			a.append(ea)
		return a
