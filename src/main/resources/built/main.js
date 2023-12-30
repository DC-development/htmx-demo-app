define("other", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.other = void 0;
    const other = (name) => {
        const form = document.getElementById('sortableListForm');
        /*
            form.addEventListener('end', (e: any) => {
                console.log("drop detected. this is the event: ", e.item.id.split('-')[1]);
                const droppedElement = document.getElementById(e.item.id)
                droppedElement.setAttribute('class', droppedElement.classList+' dropped')
        
            })
        */
        return `huhsu ${name}`;
    };
    exports.other = other;
});
define("main", ["require", "exports", "other"], function (require, exports, other_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    const main = () => {
        const helloString = (0, other_1.other)('PukeeSpoo');
        console.log(helloString);
    };
    main();
});
