import { IStatistics } from './IStatistics';
import { IWordMatch } from './IWordMatch';

export interface IText {
  fleschReadingScore: number;
  sentenceCount: number;
  IStatistics: IStatistics;
  words: IWordMatch[];
}
