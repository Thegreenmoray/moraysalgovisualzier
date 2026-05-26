

let canvas
let ctx;
export const nodePositions = new Map();
export let edgePositions = [];
export const listpostions = new Map();
export const matrixpostions = new Map();
const placedPoints = [];
//const minDist = 40;
let nextListID = 0;
let nextMatrixID = 0;
let nextId = 0;
let nextedgeId=0;
let labelnodeid=0;
let labeledgeid=0;
let freenodeLabels = [];
let freeedgeLabels = [];


export function nextnodeLabel() {
    if (freenodeLabels.length > 0) {
        return freenodeLabels.pop();
    }
    return labelnodeid++;
}

export function deleteNode(node) {
    freenodeLabels.push(node.visual);
}

export function nextedgeLabel() {
    if (freeedgeLabels.length > 0) {
        return freeedgeLabels.pop();
    }
    return labeledgeid++;
}

export function deleteEdge(edge) {
    freeedgeLabels.push(edge.visual);
}


export function nextid() {
    return nextId++;
}
export function init(c, context) {
    canvas = c;
    ctx = context;
}

const Visualfront = {

    init(c, context) {
       init(c, context);
    },
    remove_edge(edge){
         remove_edge(edge);
         deleteEdge(edge);
    },

removeNode(node) {
        remove_node(node);
        deleteNode(node);
},
    nextNodeId(){
        return nextid();
    },
    nextEdgeId(){
        return nextedgeId++;
    },

    addnode(node) {
        nodeformation(node)
    },

    addedge(edge) {
        edgeformation(edge)
    },


     highlightNode(nodeID) {
         return highlight_node(nodeID);},

    highlightEdge(edgeID) {
       return  highlight_Edge(edgeID);
    },

    make_node_Visible(node) {
   return  make_node_visible(node);

},

 make_node_invisible(node) {
   return make_node_Invisible(node);
},
 make_edge_visible(edgeid) {
    return  make_edge_visible(edgeid);
 },
    make_edge_invisible(edgeid) {
     return makeEdgeInvisible(edgeid);
    },


    makeAllGraphVisible(){
      return makeAllGraphVisible();
    },
    makeAllGraphInvisible(){
     return makeAllGraphInvisible();
    },
    colorNode(nodeID, color) {
       return colorNode(nodeID,color);
    },

    animateEdge(end,start) {
        return animateedge(start,end);
    },
    highlightNodeInstant(nodeID) {
       return highlightNodeInstant(nodeID);
    },
    disableNode(nodeID) {
        return disableNode(nodeID);
    },
    disableEdge(edgeID) {
      return   disableEdge(edgeID)
    },
    listpostionhighlight(listID, index) {
      return   listSquareHighlight(listID, index);
    },
    matrixpostionhighlight(matrixID,row,col) {
      return   highlightMatrixSquare(matrixID,row,col);
    },
    editMatrixSquareValue(matrixID, row, col, value){
      return   editMatrixSquareValue(matrixID, row, col, value);
    },
    editListSquareValue(listID, index, value){
     return  editListSquareValue(listID, index, value);
    },

    getpostion(x, y) {
        for (const node of nodePositions.values()) {
            console.log("Checking click:", x, y);
            console.log("Against node:", node.x, node.y);
            const dx = x - node.x;
            const dy = y - node.y;
            const rad=node.radius;
            if (Math.sqrt(dx*dx + dy*dy) <= rad) {
                return node;
            }
        }
        return null;
    },

establishset(list){
     return   renderList(establishset(list));
},
establishmatrx(matrix){
       return  renderMatrix(establishmatrix(matrix));
},


    clear() {
        clearboard()
    },
    pause(ms) {
        return pause(ms);
    },
    nextlabelId() {
    return nextnodeLabel()
    },
    nextedgelabelId(){
        return nextedgeLabel();
    }
};

export default Visualfront;


export function highlight_node(nodeID) {
   return  new Promise(resolve => {
        const node = nodePositions.get(nodeID);
        if (!node) return;

        const startColor = "#00ff00"; // green
        const endColor   = "#ff0000"; // red
        const duration   = 200;       // ms

        let startTime = null;

        function animate(timestamp) {
            if (!startTime) startTime = timestamp;
            const elapsed = timestamp - startTime;

            // progress from 0 → 1
            const t = Math.min(elapsed / duration, 1);

            // simple linear interpolation between colors
            node.color = lerpColor(startColor, endColor, t);

            // request redraw
            drawAll();

            if (t < 1) {
                requestAnimationFrame(animate);
            }
        }

        requestAnimationFrame(animate);})
}

export function animateedge(start, end){
    return new Promise(resolve => {
        const from = nodePositions.get(start);
        const to = nodePositions.get(end);

        if (!from || !to) {
            console.warn("animateEdge: missing endpoint", start, end);
            resolve();
            return;
        }

        const duration = 200;
        let startTime = null;
        function step(timestamp) {

            if (!startTime) startTime = timestamp;
            const elapsed = timestamp - startTime;
            const t = Math.min(elapsed / duration, 1);
            console.log("FRAME", t);
            // ⭐ MUST redraw the graph every frame
            drawAll();



            const x = from.x + (to.x - from.x) * t;
            const y = from.y + (to.y - from.y) * t;
            drawTravelDot(x,y);


            if (t < 1) {
                requestAnimationFrame(step);
            } else {

                resolve();

            }
        }
        requestAnimationFrame(step);
    });
}

/*
function getListCellAtPosition(x, y) {
    for (const {elements } of listpostions.values()) {
        for (const cell of elements.values()) {
            if (
                x >= cell.x && x <= cell.x + 40 &&
                y >= cell.y && y <= cell.y + 30
            ) {
                return cell;
            }
        }

    }
    return null;
}

function getMatrixCellAtPosition(x, y) {
    for (const matrix of matrixpostions.values()) {
        const { cells } = matrix;

        for (const cell of cells.values()) {
            if (
                x >= cell.x && x <= cell.x + 40 &&
                y >= cell.y && y <= cell.y + 30
            ) {
                return cell;
            }
        }
    }
    return null;
}

function generateNodePosition() {
    let attempts = 0;
    const maxAttempts = 100;

    while (attempts < maxAttempts) {
        const x = Math.random() * 1000 + 100;
        const y = Math.random() * 500 + 100;

        const tooClose = placedPoints.some(p =>
            Math.hypot(p.x - x, p.y - y) < minDist
        );

        if (!tooClose) {
            placedPoints.push({ x, y });
            return { x, y };
        }

        attempts++;
    }

    return { x: Math.random() * 1000 + 100, y: Math.random() * 500 + 100 };
}

function visualizeGraph(graph) {

    // -------------------------
    // Draw Nodes
    // -------------------------
    for (const node of graph.nodes) {
       nodeformation(node)
    }

    // -------------------------
    // Draw Edges
    // -------------------------
    for (const edge of graph.edges) {
        edgeformation(edge);
    }
}
*/
export function make_node_Invisible(nodeID) {
    return new Promise(resolve => {
        const vis = nodePositions.get(nodeID);
        if (!vis ||!vis.visible) return resolve();

        // delay 200ms
        setTimeout(() => {
            vis.visible = false;
            vis.labelVisible = false;
            const node = nodePositions.get(nodeID);
            if (!node) return;

            node.visible = false;

            for (const edge of edgePositions) {
                if (edge.from === nodeID || edge.to === nodeID) {
                    edge.visible = false;
                }
            }


            drawAll();
            resolve();
        }, 200);
    });
}

export function make_node_visible(nodeid){
    return new Promise(resolve => {
        const vis = nodePositions.get(nodeid);
        if (!vis||vis.labelVisible) return resolve();

        // delay 200ms
        setTimeout(() => {
            vis.visible = true;
            vis.labelVisible = true;
            drawAll();
            resolve();
        }, 200);
    });
}

export function nodeformation(node){
   // const pos = generateNodePosition();
    nodePositions.set(node.id,node);
       drawAll();
}

export function edgeformation(edge) {
    const from = nodePositions.get(edge.from);
    const to = nodePositions.get(edge.to);

    if (edge.directed) {
        drawArrow(ctx,from.x, from.y, to.x, to.y);
    } else {
        drawLine(from.x, from.y, to.x, to.y);
    }

    if (edge.weight) {
        const midX = (from.x + to.x) / 2;
        const midY = (from.y + to.y) / 2;
        drawWeight(edge.weight, midX, midY);
    }
    edgePositions.push(edge
    );

}

export function establishset(list) {
    const id = nextListID++;

    const elements = [];

    let pointer = 0;
    for (const e of list) {
        elements.push({
            index: pointer,
            value: e,
            x: 0,   // will be filled in by renderer
            y: 0
        });
        pointer++;
    }

    listpostions.set(id,

        { x: 50,   // list origin X
            y: 50,   // list origin Y
            elements}
        );

    return id;
}

export function renderList(listId) {
    const list = listpostions.get(listId);


    const cellWidth = 40;
    const cellHeight = 30;
    const spacing = 10;

    let currentX = list.x;
    let currentY = list.y;

    for (const cell of list.elements) {
        // assign positions
        cell.x = currentX;
        cell.y = currentY;

        // draw rectangle
        ctx.strokeRect(cell.x, cell.y, cellWidth, cellHeight);
        ctx.fillStyle = cell.color;
        // draw value
        ctx.fillText(cell.value, cell.x + spacing, cell.y + 20);

        // move to next cell (horizontal list)
        currentX += cellWidth + spacing;
    }
}

export function establishmatrix(matrix) {
    const id = nextMatrixID++;

    const rows = matrix.length;
    const cols = matrix[0].length;

    const cells = new Map();

    for (let i = 0; i < rows; i++) {
        for (let j = 0; j < cols; j++) {
            cells.set(`${i},${j}`, {
                row: i,
                col: j,
                value: matrix[i][j],
                x: 0,   // renderer fills this in
                y: 0
            });
        }
    }

    matrixpostions.set(id, { rows, cols,
        x: 50,
        y: 150,
        cells });

    return id;
}

export function renderMatrix(matrixId) {
    const matrix = matrixpostions.get(matrixId);
      const cellWidth= 40;
      const  cellHeight= 30;
       const spacing= 5;
    for (let i = 0; i < matrix.rows; i++) {
        for (let j = 0; j < matrix.cols; j++) {

            const cell = matrix.cells.get(`${i},${j}`);

            // Compute position
            cell.x = matrix.x + j * (cellWidth + spacing);
            cell.y = matrix.y + i * (cellHeight + spacing);

            // Draw rectangle
            ctx.strokeRect(cell.x, cell.y, cellWidth, cellHeight);
            ctx.fillStyle = cell.color;
            // Draw value
            ctx.fillText(
                cell.value,
                cell.x + cellWidth / 4,
                cell.y + cellHeight / 1.5
            );
        }
    }
}

function drawText(text, x, y) {
    ctx.fillStyle = "black";
    ctx.fillText(text, x - 3, y + 4);
}

 function drawLine(x1, y1, x2, y2) {
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.stroke();
}

function drawArrow(ctx, x1, y1, x2, y2) {
    const headLength = 12; // size of the arrowhead
    const angle = Math.atan2(y2 - y1, x2 - x1);

    // Draw the main line
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.stroke();

    // Draw the arrowhead
    ctx.beginPath();
    ctx.moveTo(x2, y2);

    ctx.lineTo(
        x2 - headLength * Math.cos(angle - Math.PI / 6),
        y2 - headLength * Math.sin(angle - Math.PI / 6)
    );

    ctx.lineTo(
        x2 - headLength * Math.cos(angle + Math.PI / 6),
        y2 - headLength * Math.sin(angle + Math.PI / 6)
    );

    ctx.closePath();
    ctx.fill();
}

function drawWeight(weight, x, y) {
    ctx.fillStyle = "blue";
    ctx.fillText(weight, x, y);
}

function remove_edge(edge) {
    const index = edgePositions.findIndex(e => e.id === edge.id);
    if (index !== -1) {
        edgePositions.splice(index, 1);
    }
    drawAll();

}

function remove_node(n) {

    nodePositions.delete(n.id);
    for (let i = edgePositions.length - 1; i >= 0; i--) {
        const edge = edgePositions[i];
        if (edge.to === n.visual || edge.from === n.visual) {
            edgePositions.splice(i, 1);
        }
    }
    drawAll();
}

function drawTravelDot(x, y) {
    ctx.beginPath();
    ctx.arc(x, y, 4, 0, Math.PI * 2);
    ctx.fillStyle = "green";
    ctx.fill();
}

export function drawAll() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // draw nodes
    for (const [id, node] of nodePositions) {
        drawNode(node);
    }

    // draw edges
    for (const edge of edgePositions) {
       const from = nodePositions.get(edge.from);
        const to   = nodePositions.get(edge.to);
        drawEdge(from, to,edge.id);
    }

    for (const [id] of listpostions) {
        renderList(id);
    }

    for (const [id] of matrixpostions) {
        renderMatrix(id);
    }

}

export function drawNode(node) {
    if (!node || !node.visible) return; // skip drawing

    ctx.beginPath();
    ctx.arc(node.x, node.y, node.radius, 0, Math.PI * 2);
    ctx.fillStyle = node.color;
    ctx.fill();
    ctx.strokeStyle = "red";
    ctx.stroke();

    ctx.fillStyle = "black";
    ctx.font = "14px Arial";
    ctx.textAlign = "center";
    ctx.textBaseline = "middle";
    ctx.fillText(node.visual, node.x, node.y);
}

export function drawEdge(from, to,id) {
    // Draw the line
 // Save current context state

    const vis = edgePositions.find(e => e.id === id);
    if(!vis||!vis.visible) return;
    ctx.beginPath();
    ctx.moveTo(from.x, from.y);
    ctx.lineTo(to.x, to.y);
    ctx.strokeStyle = vis.color;
    ctx.lineWidth = 2;
    ctx.stroke();

    // Draw arrowhead (make sure it doesn't interfere)
    ctx.beginPath(); // Reset path for arrow
    drawArrow(ctx, from.x, from.y, to.x, to.y);

}

export function clearboard() {
    matrixpostions.clear();
    listpostions.clear();
    nodePositions.clear();
    edgePositions.length=0;
    nextId=0;
   placedPoints.length=0;
     nextListID = 0;
     nextMatrixID = 0;
     nextedgeId=0;
     labelnodeid=0;
     labeledgeid=0;
     drawAll();

}

export function make_edge_visible(edgeid) {
    return new Promise(resolve => {
        const vis = edgePositions.find(e => e.id === edgeid);
        if (!vis || vis.visible) return resolve();

        // delay 200ms
        setTimeout(() => {
            vis.visible = true;
            vis.labelVisible = true;
            drawAll();
            resolve();
        }, 200);
    });
}

export function listSquareHighlight(listID, index) {
    return new Promise(resolve => {
        const list = listpostions.get(listID);
        if (!list) return resolve();

        const cell = list.elements[index];
        if (!cell) return resolve();

        const startColor = "#A52A2A"; // brown
        const endColor   = "#00FF00"; // green
        const duration   = 50;        // ms

        let startTime = null;

        function step(timestamp) {
            if (!startTime) startTime = timestamp;
            const elapsed = timestamp - startTime;

            const t = Math.min(elapsed / duration, 1);


            // simple linear interpolation
            cell.color = lerpColor(startColor, endColor, t);

            drawAll(); // redraw entire canvas

            if (t < 1) {
                requestAnimationFrame(step);
            } else {
                resolve(); // animation finished
            }
        }

        requestAnimationFrame(step);
    });
}

function hexToRgb(hex) {
    hex = hex.replace("#", "");
    return {
        r: parseInt(hex.substring(0, 2), 16),
        g: parseInt(hex.substring(2, 4), 16),
        b: parseInt(hex.substring(4, 6), 16)
    };
}

function rgbToHex(r, g, b) {
    return "#" +
        r.toString(16).padStart(2, "0") +
        g.toString(16).padStart(2, "0") +
        b.toString(16).padStart(2, "0");
}

function lerp(a, b, t) {
    return a + (b - a) * t;
}

function lerpColor(startHex, endHex, t) {
    const c1 = hexToRgb(startHex);
    const c2 = hexToRgb(endHex);

    const r = Math.round(lerp(c1.r, c2.r, t));
    const g = Math.round(lerp(c1.g, c2.g, t));
    const b = Math.round(lerp(c1.b, c2.b, t));

    return rgbToHex(r, g, b);
}

export function editListSquareValue(listID, index, value) {
    return new Promise(resolve => {
        const list = listpostions.get(listID);
        if (!list) return resolve();

        const cell = list.elements[index];
        if (!cell) return resolve();

        // delay 50ms then update
        setTimeout(() => {
            cell.value = value;
            drawAll();
            resolve();
        }, 50);
    });
}

export function highlightMatrixSquare(matrixID, row, col) {
    return new Promise(resolve => {
        const matrix = matrixpostions.get(matrixID);
        if (!matrix) return resolve();

        const key = `${row},${col}`;
        const cell = matrix.cells.get(key);
        if (!cell) return resolve();

        // step 1: brown immediately
        cell.color = "#A52A2A"; // brown
        drawAll();

        // step 2: green after 200ms
        setTimeout(() => {
            cell.color = "#00FF00"; // green
            drawAll();
            resolve();
        }, 200);
    });
}

export function editMatrixSquareValue(matrixID, row, col, value) {
    return new Promise(resolve => {
        const matrix = matrixpostions.get(matrixID);
        if (!matrix) return resolve();

        const key = `${row},${col}`;
        const cell = matrix.cells.get(key);
        if (!cell) return resolve();

        // delay 50ms then update
        setTimeout(() => {
            cell.value = value;
            drawAll();
            resolve();
        }, 50);
    });
}

export function colorNode(nodeID, hexColor) {
    return new Promise(resolve => {
        const node = nodePositions.get(nodeID);
        if (!node) return resolve();

        // step 1: set color immediately
        node.color = hexColor;
        drawAll();

        // step 2: after 50ms, set it again (same as Java)
        setTimeout(() => {
            node.color = hexColor;
            drawAll();
            resolve();
        }, 50);
    });
}

export function pause(ms) {
    return new Promise(resolve => {
        setTimeout(resolve, ms);
    });
}

export function highlightNodeInstant(nodeID) {
    return new Promise(resolve => {
        const node = nodePositions.get(nodeID);
        if (!node) return resolve();

        // step 1: set color immediately
        node.color = "#0000FF"; // blue
        drawAll();

        // step 2: after 100ms, set it again
        setTimeout(() => {
            node.color = "#0000FF";
            drawAll();
            resolve();
        }, 100);
    });
}

export function disableNode(nodeID) {
    return new Promise(resolve => {
        const node = nodePositions.get(nodeID);
        if (!node) return resolve();

        node.color = "#ff0000"; // red
        drawAll();

        setTimeout(() => {
            node.color = "#ff0000";
            drawAll();
            resolve();
        }, 30);
    });
}

export function highlight_Edge(edgeID) {
    return new Promise(resolve => {
        const vis = edgePositions.find(e => e.id === edgeID);
        if (!vis) return resolve();

        vis.color = "#ff0000";
        vis.width = 3;
        drawAll();

        setTimeout(() => {
            vis.color = "#ff0000";
            vis.width = 3;
            drawAll();
            resolve();
        }, 30);
    });
}

export function disableEdge(edgeID) {
    return new Promise(resolve => {

        const vis= edgePositions.find(e => e.id === edgeID);
        if (!vis) return resolve();

        vis.color = "#000000";
        vis.width = 1;
        drawAll();

        setTimeout(() => {
            vis.color = "#000000";
            vis.width = 1;
            drawAll();
            resolve();
        }, 30);
    });
}

export function makeEdgeInvisible(edge) {
    return new Promise(resolve => {
        const vis = edgePositions.find(e => e.id === edge);
        if (!vis) return resolve();

        // delay 200ms
        setTimeout(() => {
            vis.visible = false;
            vis.labelVisible = false;
            drawAll();
            resolve();
        }, 200);
    });
}

function makeAllGraphInvisible() {
    const promises = [];

    for (const node of nodePositions.keys()) {
        promises.push(make_node_Invisible(node));
    }

    for (const edge of edgePositions) {
        promises.push(makeEdgeInvisible(edge.id));
    }

    return Promise.all(promises).then(() => drawAll());
}

function makeAllGraphVisible() {
    const promises = [];

    for (const node of nodePositions.keys()) {
        promises.push(make_node_visible(node));
    }

    for (const edge of edgePositions) {
        promises.push(make_edge_visible(edge.id));
    }

    return Promise.all(promises).then(() => drawAll());
}