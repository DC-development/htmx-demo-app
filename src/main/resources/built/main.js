define("other", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.other = void 0;
    const other = (name) => {
        return `huhu ${name}`;
    };
    exports.other = other;
});
define("main", ["require", "exports", "other"], function (require, exports, other_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    const main = () => {
        const helloString = (0, other_1.other)('Pukksdiepoo');
        console.log(helloString);
    };
    main();
});
