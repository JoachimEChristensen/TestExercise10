var pizzas = ["Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4", "Pizza 5", "Pizza 6",
    "Pizza 7", "Pizza 8", "Pizza 9", "Pizza 10", "Pizza 11", "Pizza 12", "Pizza 13",
    "Pizza 14", "Pizza 15", "Pizza 16", "Pizza 17", "Pizza 18", "Pizza 19", "Pizza 20", "Pizza 21",
    "Pizza 22", "Pizza 23", "Pizza 24", "Pizza 25", "Pizza 26", "Pizza 27", "Pizza 28", "Pizza 29", "Pizza 30"];

var orders = [];

var sel = document.getElementById("PizzaList");
var ul = document.getElementById("OrderList");

var fragment = document.createDocumentFragment();

pizzas.forEach(function (pizza, index) {
    var opt = document.createElement('option');
    opt.innerHTML = pizza;
    opt.value = pizza;
    fragment.appendChild(opt);
});
sel.appendChild(fragment);

document.getElementById("btn").onclick = function newPizza() {
    var now = new Date();
    
    var newOrder = sel.value + " ordered at: " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
    
    orders.push(newOrder);
    
    var li = document.createElement("li");
    li.setAttribute('id', orders[orders.length-1]);
    var btn = document.createElement("button");
    btn.innerText = "Remove";
    li.appendChild(document.createTextNode(orders[orders.length-1]));
    li.appendChild(btn);
    ul.appendChild(li);
};

function finishPizza(number) {
    ul.removeChild(document.getElementsByTagName('li')[number]);
}