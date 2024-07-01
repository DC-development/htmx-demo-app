import {sum} from './sum'
// @ts-ignore
import {describe, expect, test} from '@jest/globals';


describe('sum module', () => {
    let resultNumber = 4;
    test(`adds 2 + 2 to equal ${resultNumber}`, () => {
        expect(sum(2, 2)).toBe(4);
    });
});
