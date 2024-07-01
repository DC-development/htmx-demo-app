import {SortableList} from "./sortable-list";

import {describe, expect, test} from '@jest/globals';

describe('test list', () => {
    test('should run sortable-script and return nodelist', () => {
        console.log(SortableList('.sortable'))
        expect(SortableList('.sortable')).toBeTruthy();
    });
});
