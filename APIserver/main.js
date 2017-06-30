let express = require('express')
let bodyParser = require('body-parser')
let app = express()

// For test proposes, the "database" is described bellow
const ingredients = {
	1: {id: 1, name: 'Alface', price: 0.40, image: 'https://bs.simplusmedia.com/i/f/o/saude/conteudo/alface2.jpg'},
	2: {id: 2, name: 'Bacon', price: 2.00, image: 'https://entreamigoslgbt.org/wp-content/uploads/2017/05/free-bacon-clipart-mlp-oc-mrbacon-cutiemark-philiptomkins-on-deviantart-free-clipart.png'},
	3: {id: 3, name: 'Hamburguer de Carne', price: 7.00, image: 'http://images.guiadohamburguer.com/fotos/385-paulista-burger-colono/01-colono-burger-paulista-burger.jpg'},
	4: {id: 4, name: 'Ovo', price: 0.80, image: 'http://curaverde.com.br/wp-content/uploads/2016/11/ovo-frito.jpg'},
	5: {id: 5, name: 'Queijo', price: 1.50, image: 'http://cliparting.com/wp-content/uploads/2016/12/Cheese-clip-art-free-clipart-images.jpg'},
	6: {id: 6, name: 'Pão com gergelim', price: 1.00, image: 'https://st.depositphotos.com/1000938/3573/i/170/depositphotos_35734339-stock-photo-hamburger-buns-isolated.jpg'}
}

const sandwiches = {
	1: {id: 1, name: 'X-Bacon', ingredients: [2, 3, 5, 6], image: 'http://4.bp.blogspot.com/-04M3ljOud1Q/UO9owTBtSYI/AAAAAAAAAsc/gq0c1-knR-o/s1600/double_cheeseburger_bacon_0.png'},
	2: {id: 2, name: 'X-Burger', ingredients: [3, 5, 6], image: 'http://1.bp.blogspot.com/-KMnNOktFdPI/Ua0uV0-vMYI/AAAAAAAABEU/vuGC3pSn6gw/s1600/x-burguer+bem+feito.png'},
	3: {id: 3, name: 'X-Egg', ingredients: [3, 4, 5, 6], image: 'http://www.salgadosdefestasp.com.br/files/48.jpg'},
	4: {id: 4, name: 'X-Egg Bacon', ingredients: [1, 2, 3, 4, 5, 6], image: 'http://netrango.com.br/admin/clients/contatoitalianapizzariacombr/images/14184877252135752064.jpg'}
}

const promos = {
	1: {id: 1, name: 'Light', description: 'Se o lanche tem alface e não tem bacon, ganha 10% de desconto.'},
	2: {id: 2, name: 'Muita carne', description: 'A cada 3 porções de carne só paga 2. Se o lanche tiver 6 porções, pagará 4. Assim por diante'},
	3: {id: 3, name: 'Muito queijo', description: 'A cada 3 porções de queijo só paga 2. Se o lanche tiver 6 porções, pagará 4. Assim por diante'}
}

let orders = {}

// Basic configuration
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: false }))
app.listen(8080)

// Business logic
const map_to_array = (map) => {
	let list = []
	Object.keys(map).forEach(key => list.push(map[key]))
	return list
}

const list_endpoints = (res) =>	res.send('Valid REST routes:\n/api/pedido\n/api/ingrediente\n/api/lanche\n/api/promocao')
const list_sandwiches = (res) => res.json(map_to_array(sandwiches))
const list_ingredients = (res) => res.json(map_to_array(ingredients))
const list_promos = (res) => res.json(map_to_array(promos))
const list_orders = (res) => res.json(map_to_array(orders))

const get_sandwich = (id, res) => {
	if (!sandwiches[id]) {
		return res.status(404).send('Id not found')
	}

	res.json(sandwiches[id])
}

const list_ingredients_of_sandwich = (id, res) => {
	if (!sandwiches[id]) {
		return res.status(404).send('Id not found')
	}

	let i = []
	sandwiches[id].ingredients.forEach(id => i.push(ingredients[id]))
	res.json(i)
}

const add_order = (id_sandwich, extras, res) => {
	if (!sandwiches[id_sandwich]) {
		return res.status(400).send('Invalid id')
	}

	extras = extras ? JSON.parse(extras) : []
	let last_id = Object.keys(orders).slice(-1)
	let id = last_id ? parseInt(last_id) + 1 : 1
	let order = {id, id_sandwich, extras, date: new Date().getTime()}
	orders[id] = order
	res.json(order)
}


// Routing
app.get('/', (req, res) => list_endpoints(res))
app.get('/api', (req, res) => list_endpoints(res))

app.get('/api/lanche', (req, res) => list_sandwiches(res))
app.get('/api/lanche/:id_sandwich', (req, res) => get_sandwich(req.params.id_sandwich, res))

app.get('/api/ingrediente', (req, res) => list_ingredients(res))
app.get('/api/ingrediente/de/:id_sandwich', (req, res) => list_ingredients_of_sandwich(req.params.id_sandwich, res))

app.get('/api/promocao', (req, res) => list_promos(res))

app.get('/api/pedido', (req, res) => list_orders(res))
app.put('/api/pedido/:id_sandwich', (req, res) => add_order(req.params.id_sandwich, req.body.extras, res))
