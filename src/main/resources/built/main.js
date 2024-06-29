define("templates/other", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.other = void 0;
    const other = (name) => {
        return `Hello ${name}, you typescript seems ok...`;
    };
    exports.other = other;
});
define("templates/components/form/input.component", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.InputComponent = void 0;
    const InputComponent = (a) => console.log('some function called', a);
    exports.InputComponent = InputComponent;
});
define("main", ["require", "exports", "templates/other", "templates/components/form/input.component"], function (require, exports, other_1, input_component_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    const main = () => {
        const helloString = (0, other_1.other)('Philgfou!');
        console.log(helloString);
        (0, input_component_1.InputComponent)('phil');
    };
    main();
});
