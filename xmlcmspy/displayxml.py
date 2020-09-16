class SideNav(object):
	def __init__(self,arg):
		self.l = arg[0]
		self.addl = arg[1]
		self.sitel = arg[2]
		
	def printSideNav(self):
		print '<ul>'
		print '<li>Menu</li>'
		for ref in self.l:
			print '<li><a href="http://'+ref+'">'+ref+'</a></li>\n'
		print '<li>Additional References</li>'
		for ref in self.addl:
			print '<li><a href="http://'+ref+'">'+ref+'</a></li>\n'
		print '<li>Sites</li>'
		for ref in self.sitel:
			print '<li><a href="http://'+ref+'">'+ref+'</a></li>\n'
		print '</ul>'
		
		
class Figures(object):
	def __init__(self, arg):
		self.arg = arg
		
	def printFigures(self):
		print ' <ol>'
		print '   <li></li>'
		print '   <li></li>'
		print '   <li></li>'
		print ' </ol>'
		for fig in self.arg:
			print '<figure>'
			print '    <h1>'+fig[0][0]+'</h1>\n'
			print '    <p>'+fig[0][1]+'</p>\n'
			print '    <ul>\n'
			for fig1 in fig[0][2]:
				print '<li>'+fig1+'</li>'
			print '    </ul>\n'
			print '    </figure>\n'

class Articles(object):
	def __init__(self, arg):
		self.arg = arg
		
	def printArticles(self):
		print ' <article>'
		i=0
		for text in self.arg:
			if len(text[0])==2:
				print '<section><h2>'+text[0][0]+'</h2><p>'+text[0][1]+'</p><p></p></section>\n'
			if len(text[0])==3:
				print '<section><h2>'+text[0][0]+'</h2><img src="img/'+text[0][2]+'" /><p>'+text[0][1]+'</p><p></p></section>\n'
			i +=1
			if(i==3):
				print '</article><article>\n'
				i=0
		print '</article>\n'

