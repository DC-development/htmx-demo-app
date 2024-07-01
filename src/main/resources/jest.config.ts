import type {Config} from 'jest';
/**
 * For a detailed explanation regarding each configuration property, visit:
 * https://jestjs.io/docs/configuration
 */

/** @type {import('jest').Config} */
const config: Config = {
  preset: 'jest-preset-angular',
  setupFilesAfterEnv: ['<rootDir>/setup-jest.ts'],
  transformIgnorePatterns: [ ],

  globals: {

  },

  maxWorkers: 4,
  moduleDirectories: ['node_modules', '<rootDir>'],
  testEnvironment: "node",

};

module.exports = config;
