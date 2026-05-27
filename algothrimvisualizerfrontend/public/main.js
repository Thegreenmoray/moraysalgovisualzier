// Global graph state
console.log("MAIN JS START");
import Visualfront from "./visualfront.js";

const edgeLabelToId = new Map();
const nodeLabelToId = new Map();
let mode = "node";
let selectedNodeA = null;
let selectedNodeB = null;

let nodes = new Map();
let edges = new Map();
let lists = {};
let matrices = {};
let nextListID=0;
let nextMatrixID=0;


       /*
        async matrixplacer(matrix) {
            await new Promise(resolve => {
                Visualfront.establishmatrx(matrix)
                resolve();
            });
        },
        */



window.spawnNode = function(x, y) {

    const node = {
        visual:Visualfront.nextlabelId(),
        id: Visualfront.nextNodeId(),
        x,
        y,
        radius: 12,
        color: "red",
        visible: true
    };

    nodes.set(node.id, node);
    nodeLabelToId.set(node.visual,node.id);
    Visualfront.addnode(node);
};

window.createEdge = function(node1, node2) {
let directed = true;
let weight=1;
        const raw = prompt(
            "Would you like it directed? leave blank for yes or type something for undirected");
        if(raw){
         directed = false;
        }

    while (true) {
        const raw = prompt(`leave blank for one (unweighted) or type a number for weight`);
        if (raw === "") {
            // user left it blank → unweighted
            weight = 1;
            break;
        } else if (!isNaN(Number(raw))) {
            // user typed a valid number
            weight = Number(raw);
            break;
        } else {
            // user typed garbage
            alert("Please enter a valid number or leave blank");
        }
    }

    const edge = {
        visual: Visualfront.nextedgelabelId(),
        id: Visualfront.nextEdgeId(),
        from: node1.id,
        to: node2.id,
        fromlabel:node1.visual,
        tolabel:node2.visual,
        directed: directed,
        color: "black",
        weight: weight,
        visible:true
    };
    edges.set(edge.id,edge);
    edgeLabelToId.set(edge.visual,edge.id);
    Visualfront.addedge(edge);
};

window.onListCreated = function(listId, values) {
    lists[listId] = values;
};

window.onMatrixCreated = function(matrixId, values) {
    matrices[matrixId] = values;
};

window.clearResult=function (){
    nodes.clear();
    edges.clear();
    lists.length = 0;
    matrices.length=0;
    nextListID=0;
    nextMatrixID=0;
    Visualfront.clear();
    edgeLabelToId.clear();

}

window.createList = function() {
    const id = nextListID++;

    // Ask user for values
    const raw = prompt("Enter list values (comma-separated):");
    const values = raw.split(",").map(x =>
        {const trimmed = x.trim();
            const num = Number(trimmed);
            return Number.isFinite(num) ? num : trimmed;}
    );

    // Store in main state
    lists[id] = values;

    // Draw visually
    Visualfront.establishset(values);
};

window.createMatrix = function() {
    const id = nextMatrixID++;

    const rows = parseInt(prompt("Rows:"));
    const cols = parseInt(prompt("Cols:"));

    const matrix = [];
     if (isNaN(rows)){

         return;
     }
    for (let r = 0; r < rows; r++) {
        let values;
        if (isNaN(cols)){
            break;
        }
        while (true) {
            const raw = prompt(`Row ${r} values (comma-separated, exactly ${cols} values):`);
            values = raw.split(",").map(x => {
                const trimmed = x.trim();
                const num = Number(trimmed);
                return Number.isFinite(num) ? num : trimmed;});

            if (values.length === cols) break;

            alert(`You must enter exactly ${cols} values.`);
        }

        matrix[r] = values;
    }


    matrices[id] = matrix;

    Visualfront.establishmatrx(matrix);
};

async function playAnimations(anims) {
    for (const anim of anims) {
        console.log("ANIM:", anim.type);
        switch (anim.type) {

            case "matrixUpdate":
              await  Visualfront.editMatrixSquareValue(anim.matrix, anim.row, anim.col, anim.value);
                break;

            case "matrixHighlight":
              await  Visualfront.matrixpostionhighlight(anim.matrix, anim.row, anim.col);
                break;

            case "listUpdate":
               await Visualfront.editListSquareValue(anim.list, anim.index, anim.value);
                break;

            case "listHighlight":
              await  Visualfront.listpostionhighlight(anim.list, anim.index);
                break;
            case "highlightNode":
                let fs = nodeLabelToId.get(anim.node);
              await  Visualfront.highlightNode(fs);
                break;
            case "travelEdge":
                let fd = nodeLabelToId.get(anim.node);
                let fg = nodeLabelToId.get(anim.node);
                await Visualfront.animateEdge(fd, fg);
                break;
            case "lightNode":
                let fff = nodeLabelToId.get(anim.node);
               await Visualfront.highlightNodeInstant(fff);
                break;
            case "delightNode":
                let ff = nodeLabelToId.get(anim.node);
               await Visualfront.disableNode(ff);
                break;
            case "pause":
              await  Visualfront.pause(anim.ms);
                break;
            case "colorNode":
                let id = nodeLabelToId.get(anim.node);
                await Visualfront.colorNode(id,anim.color);
                break;
            case "highlightEdge":
                let ea=edgeLabelToId.get(anim.edge);
               await Visualfront.highlightEdge(ea);
                break;
            case "disableEdge":
                let ww=edgeLabelToId.get(anim.edge);
               await Visualfront.disableEdge(ww);
                break;
            case "nodeVisible":
                let df = nodeLabelToId.get(anim.node);
               await Visualfront.make_node_Visible(df);
                break;
            case "edgeVisible":
                let e=edgeLabelToId.get(anim.edge);
            await Visualfront.make_edge_visible(e);
                break;
            case "edgeInvisible":
                let es=edgeLabelToId.get(anim.edge);
              await  Visualfront.make_edge_invisible(es);
                break;
            case "nodeInvisible":
              let f = nodeLabelToId.get(anim.node);
              await  Visualfront.make_node_invisible(f);
                break;
            case "creatematrix":
                matrices.push(anim.mat);
                Visualfront.establishmatrx(anim.mat);
                break;
            case "makeinvis":
               await Visualfront.makeAllGraphInvisible();
               break;
            case "makevis":
                await Visualfront.makeAllGraphVisible();
                break;




        }






        // delay between animations
        await new Promise(r => setTimeout(r, 300));
    }
}

function getNodeAtPosition(x, y) {
    return Visualfront.getpostion(x,y);
}

document.getElementById("edgemode").onclick = () => {
    mode = "edge";
};

document.getElementById("nodemode").onclick = () => {
    mode = "node";
};

document.addEventListener("DOMContentLoaded", () => {

    const runButton = document.getElementById("runButton");
    const codeInput = document.getElementById("codeInput");

    const canvas = document.getElementById("graphCanvas");
    const ctx = canvas.getContext("2d");

    Visualfront.init(canvas, ctx);
    canvas.addEventListener("contextmenu", (e) => {
        e.preventDefault();
        e.stopPropagation();
        return false;
    });
    canvas.addEventListener("mousedown", (e) => {
        const rect = canvas.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;

        const clickedNode = getNodeAtPosition(x, y);
        const clickedEdge = getEdgeAt(x, y); // optional, later

        if (e.button === 0) {
            // LEFT CLICK
            handleLeftClick(clickedNode, x, y);
        }

        if (e.button === 2) {
            // RIGHT CLICK
            handleRightClick(clickedNode, clickedEdge);
        }
    });

    function getEdgeAt(x, y) {
        const threshold = 6;

        for (const edge of edges.values()) {
            const n1 = nodes.get(edge.from);
            const n2 = nodes.get(edge.to);

            const dist = pointToSegmentDistance(x, y, n1.x, n1.y, n2.x, n2.y);

            if (dist <= threshold) {
                return edge;
            }
        }
        return null;
    }


    function pointToSegmentDistance(px, py, x1, y1, x2, y2) {
        const A = px - x1;
        const B = py - y1;
        const C = x2 - x1;
        const D = y2 - y1;

        const dot = A * C + B * D;
        const lenSq = C * C + D * D;
        let t = dot / lenSq;

        t = Math.max(0, Math.min(1, t));

        const closestX = x1 + t * C;
        const closestY = y1 + t * D;

        const dx = px - closestX;
        const dy = py - closestY;

        return Math.sqrt(dx * dx + dy * dy);
    }

    function handleLeftClick(node, x, y) {
        if (mode === "node") {
            spawnNode(x, y);
            return;
        }

        if (mode === "edge") {
            if (!node) return;
            handleNodeClick(node);
        }
    }

    function handleRightClick(node, edge) {
        if (node &&mode === "node") {
            nodes.delete(node.id);
            Visualfront.removeNode(node);
            for(const edge of edges.values()) {
                if(edge.to === node.id ||edge.from === node.id) {
                    edges.delete(edge.id);
                    edgeLabelToId.delete(edge.visual);
                    Visualfront.remove_edge(edge);
                }
            }
            return;
        }

        if (edge&&mode==="edge") {
            edges.delete(edge.id);
            edgeLabelToId.delete(edge.visual);
            Visualfront.remove_edge(edge);

        }
    }


    function handleNodeClick(node) {
        if (!selectedNodeA) {
            selectedNodeA = node;
         //   highlightNode(node);
            return;
        }

        if (!selectedNodeB && node !== selectedNodeA) {
            selectedNodeB = node;
         //   highlightNode(node);

            createEdge(selectedNodeA, selectedNodeB);
            resetSelection();
        }
    }

    function resetSelection() {
        selectedNodeA = null;
        selectedNodeB = null;
      //  renderer.clearHighlights();
    }

    async function runAlgorithm() {
        const payload = {
            nodes: Array.from(nodes.values()).map(n => ({ id: n.visual})),
            edges: Array.from(edges.values()).map(e => ({
                edge:e.visual,
                from: e.from,
                to: e.to,
                directed: e.directed,
                weight: e.weight
            })),
            lists: lists,        // <--- ADD THIS
            matrices: matrices,  // <--- AND THIS
            algorithm: codeInput.value
        };
//should be 8082, but for testing use 8081
        const response = await fetch("http://localhost:8081/run", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        return await response.json();
    }

    runButton.onclick = async () => {
        console.log("Run button clicked");

        const result = await runAlgorithm();
        console.log("Result:", result);
        console.log("Animations:", result.animations);
        await playAnimations(result.animations);
    };
});
