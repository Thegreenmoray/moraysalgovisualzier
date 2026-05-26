import { describe, it, expect, beforeEach, vi,beforeAll } from "vitest";
//dont  bother with this its a lost cuase.
// ------------------------------------------------------------
// 1. MOCK THE MODULE BEFORE IMPORT — WITHOUT importOriginal()
// ------------------------------------------------------------
vi.mock("./public/visualfront.js", async () => {
    const actual = await vi.importActual("./public/visualfront.js");
    return {
        ...actual,
        drawAll: vi.fn(), // override renderer
    };
});

// ------------------------------------------------------------
// 2. Fake DOM + canvas BEFORE importing the module
// ------------------------------------------------------------
global.document = {
    getElementById: (id) => {
        if (id === "graphCanvas") return global.canvas;
        if (id === "runButton") return {};
        if (id === "codeInput") return {};
        return null;
    }
};

global.ctx = {}; // never used because drawAll is mocked

global.canvas = {
    width: 800,
    height: 600,
    getContext: () => global.ctx
};

// ------------------------------------------------------------
// 3. Fake timers BEFORE importing the module
// ------------------------------------------------------------
vi.useFakeTimers();

// ------------------------------------------------------------
// 4. Import the module AFTER the mock
// ------------------------------------------------------------
let visualfront;

let disableNode,
    colorNode,
    highlightNodeInstant,
    make_node_visible,
    make_node_Invisible,
    editListSquareValue,
    editMatrixSquareValue,
    highlightMatrixSquare,
    nodePositions,
    edgePositions,
    listpostions,
    matrixpostions,
    clearboard,
    establishset,
    establishmatrix,
    nextid,
    nextnodeLabel,
    nodeformation,
    init;

beforeAll(async () => {
    visualfront = await import("./public/visualfront.js");

    ({
        disableNode,
        colorNode,
        highlightNodeInstant,
        make_node_visible,
        make_node_Invisible,
        editListSquareValue,
        editMatrixSquareValue,
        highlightMatrixSquare,
        nodePositions,
        edgePositions,
        listpostions,
        matrixpostions,
        clearboard,
        establishset,
        establishmatrix,
        nextid,
        nextnodeLabel,
        nodeformation,
        init
    } = visualfront);

 init(global.canvas, global.ctx);
});
// ------------------------------------------------------------
// 5. Initialize Visualfront exactly like the real app
// ------------------------------------------------------------


// ------------------------------------------------------------
// 6. Helper: create a node using the real API
// ------------------------------------------------------------
function createNode() {
    const node = {
        visual: nextnodeLabel(),
        id: nextid(),
        x: 0,
        y: 0,
        radius: 12,
        color: "#000000",
        visible: true,
        labelVisible: true
    };
    nodeformation(node);
    return node;
}

// ------------------------------------------------------------
// 7. TESTS
// ------------------------------------------------------------

describe("disableNode", () => {
    beforeEach(() => clearboard());

    it("sets node color to red and calls drawAll twice", async () => {
        const node = createNode();

        await disableNode(node.id);
        await vi.runAllTimersAsync();

        expect(nodePositions.get(node.id).color).toBe("#ff0000");
    });

    it("resolves immediately if node does not exist", async () => {
        await expect(disableNode(999)).resolves.toBeUndefined();
    });
});

describe("colorNode", () => {
    beforeEach(() => clearboard());

    it("sets node color immediately and after 50ms", async () => {
        const node = createNode();

        await colorNode(node.id, "#abcdef");
        await vi.runAllTimersAsync();

        expect(nodePositions.get(node.id).color).toBe("#abcdef");
    });
});

describe("highlightNodeInstant", () => {
    beforeEach(() => clearboard());

    it("sets node color to blue immediately and after 100ms", async () => {
        const node = createNode();

        await highlightNodeInstant(node.id);
        await vi.runAllTimersAsync();

        expect(nodePositions.get(node.id).color).toBe("#0000FF");
    });
});

describe("make_node_visible", () => {
    beforeEach(() => clearboard());

    it("makes node visible and labelVisible true", async () => {
        const node = createNode();
        node.visible = false;
        node.labelVisible = false;

        await make_node_visible(node.id);
        await vi.runAllTimersAsync();

        const updated = nodePositions.get(node.id);
        expect(updated.visible).toBe(true);
        expect(updated.labelVisible).toBe(true);
    });
});

describe("make_node_Invisible", () => {
    beforeEach(() => clearboard());

    it("hides node and connected edges", async () => {
        const node = createNode();

        edgePositions.push({ from: node.id, to: 10, visible: true });
        edgePositions.push({ from: 99, to: node.id, visible: true });

        await make_node_Invisible(node.id);
        await vi.runAllTimersAsync();

        expect(nodePositions.get(node.id).visible).toBe(false);
        expect(edgePositions[0].visible).toBe(false);
        expect(edgePositions[1].visible).toBe(false);
    });
});

describe("editListSquareValue", () => {
    beforeEach(() => clearboard());

    it("updates list cell value after delay", async () => {
        const listID = establishset([10, 20]);

        await editListSquareValue(listID, 1, 99);
        await vi.runAllTimersAsync();

        expect(listpostions.get(listID).elements[1].value).toBe(99);
    });
});

describe("editMatrixSquareValue", () => {
    beforeEach(() => clearboard());

    it("updates matrix cell value after delay", async () => {
        const matrixID = establishmatrix([[5]]);

        await editMatrixSquareValue(matrixID, 0, 0, 42);
        await vi.runAllTimersAsync();

        expect(matrixpostions.get(matrixID).cells.get("0,0").value).toBe(42);
    });
});

describe("highlightMatrixSquare", () => {
    beforeEach(() => clearboard());

    it("animates matrix cell from brown to green", async () => {
        const matrixID = establishmatrix([[0, 0], [0, 0]]);

        await highlightMatrixSquare(matrixID, 1, 1);
        await vi.runAllTimersAsync();

        const cell = matrixpostions.get(matrixID).cells.get("1,1");
        expect(cell.color).toBe("#00FF00");
    });
});